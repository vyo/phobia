package io.github.vyo.strakh.application

import bwapi.DefaultBWListener
import bwapi.Mirror
import bwapi.UnitType
import bwta.BWTA
import io.github.vyo.strakh.goap.engine.Executor
import io.github.vyo.strakh.goap.engine.Planner
import io.github.vyo.strakh.log.Logger
import io.github.vyo.strakh.model.agent.Worker
import io.github.vyo.strakh.model.game.World
import nl.komponents.kovenant.async

/**
 * Created by Manuel Weidmann on 21.11.2015.
 */

object Strakh : DefaultBWListener() {
    init {
        World.meta.mirror = Mirror()
    }

    val logger: Logger = Logger(this)

    override fun onStart() {

        logger.info("Bot is ready to rock!")

        World.meta.game = World.meta.mirror.game
        World.players.self = World.meta.game.self()

        //Use BWTA to analyze map
        //This may take a few minutes if the map is processed first time!
        async {
            logger.info("Analysing map...")
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
        World.update()
        //game.setTextSize(10);
        World.meta.game.drawTextScreen(10, 10, "Playing as " + World.players.self.name + " - " + World.players.self
                .race)

        val units = StringBuilder("My units:\n")

        //iterate through my units
        for (myUnit in World.units.own) {
            units.append(myUnit.type).append(" ").append(myUnit.tilePosition).append("\n")

            var plan = Planner.formulatePlan(Worker(myUnit))
            val (dummyPlan, cost, steps) = Planner.returnPlan()
            //if it's a drone and it's idle, send it to the closest mineral patch
            if (myUnit.type.isWorker && myUnit.isIdle) {
                Executor.executePlan(plan)
            }

            //if there's enough minerals, train an SCV
            if (myUnit.type === UnitType.Terran_Command_Center && World.players.self.minerals() >= 50) {
                myUnit.train(UnitType.Terran_SCV)
            }
        }

        //draw my units on screen
        World.meta.game.drawTextScreen(10, 25, units.toString())
    }

    fun start() {
        World.meta.mirror.module.setEventListener(this)
        World.meta.mirror.startGame()
    }
}
