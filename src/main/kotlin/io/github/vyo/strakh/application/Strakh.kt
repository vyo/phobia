package io.github.vyo.strakh.application

import bwapi.*
import bwapi.Unit
import bwta.BWTA
import io.github.vyo.strakh.goap.engine.Executor
import io.github.vyo.strakh.goap.engine.Planner
import io.github.vyo.strakh.model.agent.Worker
import io.github.vyo.strakh.model.game.Players
import io.github.vyo.strakh.model.game.Units
import io.github.vyo.strakh.model.game.World
import io.github.vyo.twig.Level
import io.github.vyo.twig.Logger
import nl.komponents.kovenant.async

/**
 * Created by Manuel Weidmann on 21.11.2015.
 */

object Strakh : BWEventListener {
    val logger: Logger = Logger("strakh")
    val gameInfoLogger: Logger = Logger("game")
    val frameInfoLogger: Logger = Logger("frame")

    init {
        World.meta.mirror = Mirror()
    }

    override fun onStart() {
        World.meta.game = World.meta.mirror.game
        Players.self = World.meta.game.self()

        Planner.logger.threshold = Level.DEBUG
        gameInfoLogger.threshold = Level.DEBUG

        gameInfoLogger.info("Bot $this")
        gameInfoLogger.info("BWAPI revision ${World.meta.game.revision} ")
        if (World.meta.game.isDebug) gameInfoLogger.info("Debug mode")
        if (World.meta.game.isMultiplayer) {
            gameInfoLogger.info("Multiplayer")
        } else {
            gameInfoLogger.info("Singleplayer")
        }
        if (World.meta.game.isBattleNet) gameInfoLogger.info("Battle.net")
        gameInfoLogger.info("Map ${World.meta.game.mapName()} (${World.meta.game.mapPathName()})")
        if (World.meta.game.isReplay) gameInfoLogger.info("Replay")

        async {
            logger.info("Setting up BWTA map data")
            BWTA.readMap()
            BWTA.analyze()
        } success {
            logger.info("Map data ready")
            World.meta.mapAnalysed = true
        } fail {
            logger.warn("Map data not ready")
            World.meta.mapAnalysed = false
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
        logger.debug("New unit " + unit.type)
    }

    override fun onFrame() {
        frameInfo()

        World.update()
        //game.setTextSize(10);
        World.meta.game.drawTextScreen(10, 10, "Playing as " + Players.self.name + " - " + Players.self
                .race)

        val units = StringBuilder("My units:\n")

        //iterate through my units
        for (myUnit in Units.own) {
            units.append(myUnit.type).append(" ").append(myUnit.tilePosition).append("\n")

            //if it's a drone and it's idle, send it to the closest mineral patch
            if (myUnit.type.isWorker && myUnit.isIdle) {
                var plan = Planner.formulatePlan(Worker(myUnit))
                Executor.executePlan(plan)
            }

            //if there's enough minerals, train an SCV
            if (myUnit.type === UnitType.Terran_Command_Center && Players.self.minerals() >= 50) {
                myUnit.train(UnitType.Terran_SCV)
            }
        }

        //draw my units on screen
        World.meta.game.drawTextScreen(10, 25, units.toString())
    }

    override fun onUnitHide(unit: Unit?) {
        gameInfoLogger.trace("event callback for onUnitHide not implemented")
    }

    override fun onUnitComplete(unit: Unit?) {
        gameInfoLogger.trace("event callback for onUnitComplete not implemented")
    }

    override fun onSendText(text: String) {
        gameInfoLogger.trace("event callback for onSendText not implemented")
    }

    override fun onNukeDetect(target: Position?) {
        gameInfoLogger.trace("event callback for onNukeDetect not " +
                "implemented")
    }

    override fun onPlayerDropped(player: Player?) {
        gameInfoLogger.trace("event callback for onPlayerDropped not implemented")
    }

    override fun onUnitEvade(unit: Unit?) {
        gameInfoLogger.trace("event callback for onUnitEvade not implemented")

    }

    override fun onEnd(end: Boolean) {
        gameInfoLogger.trace("event callback for onEnd not implemented")
    }

    override fun onUnitMorph(unit: Unit?) {
        gameInfoLogger.trace("event callback for onUnitMorph not implemented")
    }

    override fun onUnitRenegade(unit: Unit?) {
        gameInfoLogger.trace("event callback for onUnitRenegade not implemented")
    }

    override fun onUnitDiscover(unit: Unit?) {
        gameInfoLogger.trace("event callback for onUnitDiscover not implemented")
    }

    override fun onPlayerLeft(player: Player?) {
        gameInfoLogger.trace("event callback for onPlayerLeft not implemented")
    }

    override fun onReceiveText(receiver: Player?, text: String?) {
        gameInfoLogger.trace("event callback for onReceiveText not implemented")
    }

    override fun onUnitShow(unit: Unit?) {
        gameInfoLogger.trace("event callback for onUnitShow not implemented")
    }

    override fun onSaveGame(save: String?) {
        gameInfoLogger.trace("event callback for onSaveGame not implemented")
    }

    override fun onUnitDestroy(unit: Unit?) {
        gameInfoLogger.trace("event callback for onUnitDestroy not implemented")
    }

    fun start() {
        World.meta.mirror.module.setEventListener(this)
        World.meta.mirror.startGame()
    }

    private fun frameInfo(level: Level = Level.TRACE) {
        frameInfoLogger.log("frame count    ${World.meta.game.frameCount}", level)
        frameInfoLogger.log("apm total      ${World.meta.game.getAPM(false)}", level)
        frameInfoLogger.log("apm net        ${World.meta.game.getAPM(true)}", level)
        frameInfoLogger.log("fps current    ${World.meta.game.fps}", level)
        frameInfoLogger.log("fps average    ${World.meta.game.averageFPS}", level)
        frameInfoLogger.log("latency        ${World.meta.game.latency}", level)
        frameInfoLogger.log("latency frames ${World.meta.game.latencyFrames}", level)
        frameInfoLogger.log("latency time   ${World.meta.game.latencyTime}", level)
    }
}
