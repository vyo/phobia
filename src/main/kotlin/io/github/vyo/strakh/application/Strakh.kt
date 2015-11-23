package io.github.vyo.strakh.application

import bwapi.DefaultBWListener
import bwapi.Mirror
import bwapi.UnitType
import bwta.BWTA
import io.github.vyo.strakh.goap.engine.Executor
import io.github.vyo.strakh.goap.engine.Planner
import io.github.vyo.strakh.model.agent.Worker
import io.github.vyo.strakh.model.game.World

/**
 * Created by Manuel Weidmann on 21.11.2015.
 */

class Strakh : DefaultBWListener() {

    //    val mirror: Mirror = Mirror()
    //    lateinit var game: Game
    //    lateinit var self: Player

    init {
        World.meta.mirror = Mirror()
    }

    override fun onStart() {
        //        game = mirror.game
        //        self = game.self()

        //        World.meta.mirror = Mirror()
        World.meta.game = World.meta.mirror.game
        World.players.self = World.meta.game.self()

        //Use BWTA to analyze map
        //This may take a few minutes if the map is processed first time!
        println("Analyzing map...")
        BWTA.readMap()
        BWTA.analyze()
        println("Map data ready")

        var i = 0
        for (baseLocation in BWTA.getBaseLocations()) {
            println("Base location #" + (++i) + ". Printing location's region polygon:")
            for (position in baseLocation.region.polygon.points) {
                print(position.toString() + ", ")
            }
            println()
        }
    }

    override fun onUnitCreate(unit: bwapi.Unit) {
        println("New unit " + unit.type)
    }

    override fun onFrame() {
        //game.setTextSize(10);
        World.meta.game.drawTextScreen(10, 10, "Playing as " + World.players.self.name + " - " + World.players.self
                .race)

        val units = StringBuilder("My units:\n")

        //update my units
        World.units.own = World.players.self.units

        for (unit in World.units.own) println(unit)
        //iterate through my units
        for (myUnit in World.units.own) {
            var plan = Planner.formulatePlan(Worker(myUnit))

            units.append(myUnit.type).append(" ").append(myUnit.tilePosition).append("\n")

            //if there's enough minerals, train an SCV
            if (myUnit.type === UnitType.Terran_Command_Center && World.players.self.minerals() >= 50) {
                myUnit.train(UnitType.Terran_SCV)
            }

            //if it's a drone and it's idle, send it to the closest mineral patch
            if (myUnit.type.isWorker && myUnit.isIdle) {

                Executor.executePlan(plan)
                //                var closestMineral: bwapi.Unit? = null
                //
                //                //find the closest mineral
                //                for (neutralUnit in game.neutral().units) {
                //                    if (neutralUnit.type.isMineralField) {
                //                        if (closestMineral == null || myUnit.getDistance(neutralUnit) < myUnit.getDistance(closestMineral)) {
                //                            closestMineral = neutralUnit
                //                        }
                //                    }
                //                }
                //
                //                //if a mineral patch was found, send the drone to gather it
                //                if (closestMineral != null) {
                //                    myUnit.gather(closestMineral, false)
                //                }
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
