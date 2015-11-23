package io.github.vyo.strakh.model.game

import bwapi.Unit
import bwapi.UnitType

/**
 * Created by Manuel Weidmann on 23.11.2015.
 */

object Resources : State() {
    var minerals: MutableList<Unit> = arrayListOf()
    var geysers: MutableList<Unit> = arrayListOf()
    var refineries: MutableList<Unit> = arrayListOf()

    data class ResourceSpot(var resource: Unit?, var nearby: Boolean = false, var
    saturated: Boolean = false, var hasRefinery: Boolean = false)

    fun nearestMineralPatch(worker: Unit): ResourceSpot {
        //find the closest mineral
        var closestPatch: bwapi.Unit? = null
        for (mineralPatch in minerals) {
            if (closestPatch == null || worker.getDistance(mineralPatch) < worker.getDistance(closestPatch)) {
                closestPatch = mineralPatch
            }
        }

        val nearby = true
        val saturated = false
        return ResourceSpot(closestPatch, nearby, saturated)
        //        if (closestMineral is Unit) return closestMineral
        //        else throw UnsupportedOperationException()
    }

    fun nearestGeyser(worker: bwapi.Unit): ResourceSpot {
        //find the closest geyser
        var closestGeyser: bwapi.Unit? = null
        for (geyser in geysers) {
            if (closestGeyser == null || worker.getDistance(geyser) < worker.getDistance(closestGeyser)) {
                closestGeyser = geyser
            }
        }

        val nearby = true
        val saturated = true
        return ResourceSpot(closestGeyser, nearby, saturated)
        //        return false
    }

    fun nearestRefinery(worker: Unit): ResourceSpot {
        //find the closest refinery
        var closestRefinery: bwapi.Unit? = null
        for (refinery in refineries) {
            if (closestRefinery == null || worker.getDistance(refinery) < worker.getDistance(closestRefinery)) {
                closestRefinery = refinery
            }
        }

        val nearby = true
        val saturated = true
        return ResourceSpot(closestRefinery, nearby, saturated)
        //        return false
    }

    override fun update() {
        //TODO: update re-actively instead of looping each frame
        //update mineral patches
        minerals.clear()
        for (patch in World.units.neutral) {
            if (patch.type == UnitType.Resource_Mineral_Field
                    || patch.type == UnitType.Resource_Mineral_Field_Type_2
                    || patch.type == UnitType.Resource_Mineral_Field_Type_3) {
                minerals.add(patch)
            }
        }
        //update vespene geysers
        geysers.clear()
        for (geyser in World.units.neutral) {
            if (geyser.type == UnitType.Resource_Vespene_Geyser) {
                geysers.add(geyser)
            }
        }
        //update refineries
        refineries.clear()
        for (refinery in World.units.own) {
            if (refinery.type.isRefinery) {
                refineries.add(refinery)
            }
        }
    }
}