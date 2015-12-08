package io.github.vyo.strakh.application

import bwapi.*
import bwapi.Unit
import bwta.BWTA
import io.github.vyo.strakh.goap.engine.Executor
import io.github.vyo.strakh.goap.engine.Planner
import io.github.vyo.strakh.model.agent.Worker
import io.github.vyo.strakh.model.game.Meta
import io.github.vyo.strakh.model.game.Players
import io.github.vyo.strakh.model.game.Units
import io.github.vyo.strakh.model.game.World
import io.github.vyo.twig.logger.Level
import io.github.vyo.twig.logger.Logger
import nl.komponents.kovenant.async
import nl.komponents.kovenant.then

/**
 * Created by Manuel Weidmann on 21.11.2015.
 */

object Strakh : BWEventListener {
    val logger: Logger = Logger("strakh")
    val gameInfoLogger: Logger = Logger("game")
    val frameInfoLogger: Logger = Logger("frame")

    init {
        Meta.mirror = Mirror()
        frameInfoLogger.level = Level.INFO
    }

    override fun onStart() {
        Meta.game = Meta.mirror.game
        Players.self = Meta.game.self()
        Players.enemies = Meta.game.enemies()

        async {
            Meta.game.enemies()
        } then {
            logger.info(it.toString())
        }

        //        Planner.logger.level = Level.DEBUG
        //        Executor.logger.level = Level.TRACE
        //        gameInfoLogger.level = Level.INFO

        gameInfoLogger.info("Bot $this")
        gameInfoLogger.info("BWAPI revision ${Meta.game.revision}")
        if (Meta.game.isDebug) gameInfoLogger.info("Debug mode")
        if (Meta.game.isMultiplayer) {
            gameInfoLogger.info("Multiplayer")
        } else {
            gameInfoLogger.info("Singleplayer")
        }
        if (Meta.game.isBattleNet) gameInfoLogger.info("Battle.net")
        gameInfoLogger.info("Map ${Meta.game.mapName()} (${Meta.game.mapPathName()})")
        if (Meta.game.isReplay) gameInfoLogger.info("Replay")

        async {
            gameInfoLogger.info("Setting up BWTA map data")
            BWTA.readMap()
            BWTA.analyze()
        } success {
            gameInfoLogger.info("Map data ready")
            Meta.mapAnalysed = true
        } fail {
            gameInfoLogger.warn("Map data not ready")
            Meta.mapAnalysed = false
        }


        var i = 0
        for (baseLocation in BWTA.getBaseLocations()) {
            logger.info("Base location #" + (++i) + ". Printing location's region polygon:")
            for (position in baseLocation.region.polygon.points) {
                logger.info(position.toString() + ", ")
            }
        }
    }

    override fun onUnitCreate(unit: bwapi.Unit) {
        gameInfoLogger.debug("New unit " + unit.type)
    }

    override fun onFrame() {
        frameInfo()

        World.update()
        //game.setTextSize(10);
        //        Game.game.drawTextScreen(10, 10, "Playing as " + Players.self.name + " - " + Players.self
        //                .race)

        //        val units = StringBuilder("My units:\n")

        //iterate through my units
        for (myUnit in Units.own) {
            //            units.append(myUnit.type).append(" ").append(myUnit.tilePosition).append("\n")

            //if it's a drone and it's idle, send it to the closest mineral patch
            if (myUnit.type.isWorker && myUnit.isIdle) {

                async {
                    Planner.formulatePlan(Worker(myUnit))
                }.get()

                //                async {
                //                    Planner.formulatePlan(Worker(myUnit))
                //                } then {
                //                    Executor.executePlan(it)
                //                }

                //                async {
                //                    val plan = Planner.formulatePlan(Worker(myUnit))
                //                    Executor.executePlan(plan)
                //                }
            }

            //if there's enough minerals, train an SCV
            if (myUnit.type === UnitType.Terran_Command_Center && Players.self.minerals() >= 50) {
                myUnit.train(UnitType.Terran_SCV)
            }
        }

        //draw my units on screen
        //        Game.game.drawTextScreen(10, 25, units.toString())

        Executor.processQueue()
    }

    override fun onUnitHide(unit: Unit?) {
        gameInfoLogger.debug("event callback for onUnitHide not implemented")
    }

    override fun onUnitComplete(unit: Unit?) {
        gameInfoLogger.debug("event callback for onUnitComplete not implemented")
    }

    override fun onSendText(text: String) {
        gameInfoLogger.debug("event callback for onSendText not implemented")
    }

    override fun onNukeDetect(target: Position?) {
        gameInfoLogger.debug("event callback for onNukeDetect not " +
                "implemented")
    }

    override fun onPlayerDropped(player: Player?) {
        gameInfoLogger.debug("event callback for onPlayerDropped not implemented")
    }

    override fun onUnitEvade(unit: Unit?) {
        gameInfoLogger.debug("event callback for onUnitEvade not implemented")

    }

    override fun onEnd(end: Boolean) {
        gameInfoLogger.debug("event callback for onEnd not implemented")
    }

    override fun onUnitMorph(unit: Unit?) {
        gameInfoLogger.debug("event callback for onUnitMorph not implemented")
    }

    override fun onUnitRenegade(unit: Unit?) {
        gameInfoLogger.debug("event callback for onUnitRenegade not implemented")
    }

    override fun onUnitDiscover(unit: Unit?) {
        gameInfoLogger.debug("event callback for onUnitDiscover not implemented")
    }

    override fun onPlayerLeft(player: Player?) {
        gameInfoLogger.debug("event callback for onPlayerLeft not implemented")
    }

    override fun onReceiveText(receiver: Player?, text: String?) {
        gameInfoLogger.debug("event callback for onReceiveText not implemented")
    }

    override fun onUnitShow(unit: Unit?) {
        gameInfoLogger.debug("event callback for onUnitShow not implemented")
    }

    override fun onSaveGame(save: String?) {
        gameInfoLogger.debug("event callback for onSaveGame not implemented")
    }

    override fun onUnitDestroy(unit: Unit?) {
        gameInfoLogger.debug("event callback for onUnitDestroy not implemented")
    }

    fun start() {
        Meta.mirror.module.setEventListener(this)
        Meta.mirror.startGame()
    }

    private fun frameInfo() {
        frameInfoLogger.trace("frame count    ${Meta.game.frameCount}")
        frameInfoLogger.trace("apm total      ${Meta.game.getAPM(false)}")
        frameInfoLogger.trace("apm net        ${Meta.game.getAPM(true)}")
        frameInfoLogger.trace("fps current    ${Meta.game.fps}")
        frameInfoLogger.trace("fps average    ${Meta.game.averageFPS}")
        frameInfoLogger.trace("latency        ${Meta.game.latency}")
        frameInfoLogger.trace("latency frames ${Meta.game.latencyFrames}")
        frameInfoLogger.trace("latency time   ${Meta.game.latencyTime}")
    }
}
