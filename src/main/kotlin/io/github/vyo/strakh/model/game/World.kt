package io.github.vyo.strakh.model.game

import bwapi.Game
import bwapi.Mirror
import bwapi.Player
import bwapi.Unit

/**
 * Created by Manuel Weidmann on 22.11.2015.
 */

object World {

    object meta {
        lateinit var mirror: Mirror
        lateinit var game: Game
    }

    object players {
        lateinit var self: Player
    }

    object units {
        lateinit var neutral: MutableList<Unit>
        lateinit var own: MutableList<Unit>
        lateinit var enemy: MutableList<Unit>
    }

    fun freeMineralPatchNearby(worker: bwapi.Unit): Boolean {
        return true
    }

    fun nearestFreeMineralPatch(worker: Unit): Unit {
        //find the closest mineral
        var closestMineral: bwapi.Unit? = null
        for (neutralUnit in meta.game.neutral().units) {
            if (neutralUnit.type.isMineralField) {
                if (closestMineral == null || worker.getDistance(neutralUnit) < worker.getDistance(closestMineral)) {
                    closestMineral = neutralUnit
                }
            }
        }
        if (closestMineral is Unit) return closestMineral
        else throw UnsupportedOperationException()
    }

    fun freeGeyserNearby(worker: bwapi.Unit): Boolean {
        return false
    }

    fun freeRefineryNearby(worker: Unit): Boolean {
        return false
    }

}