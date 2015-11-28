package io.github.vyo.strakh.model.action

import io.github.vyo.strakh.goap.component.Action
import io.github.vyo.strakh.goap.component.Cost
import io.github.vyo.strakh.goap.component.NotExecutableException
import io.github.vyo.strakh.model.agent.Unit
import io.github.vyo.strakh.model.game.Resources
import io.github.vyo.twig.Logger

/**
 * Created by Manuel Weidmann on 22.11.2015.
 */

class MineMinerals(val unit: Unit) : Action {

    override var cost: Cost = Cost(actions = 1)

    val logger: Logger = Logger(this)

    override fun applicable(): Boolean {
        val spot: Resources.ResourceSpot = Resources.nearestMineralPatch(unit.unit)
        val (patch, nearby, saturated) = spot

        return patch != null && nearby && !saturated
    }

    override fun apply() {
        unit.isGatheringMinerals = true
    }

    override fun execute() {
        val (mineralPatch, nearby, saturated) = Resources.nearestMineralPatch(unit.unit)
        if (mineralPatch != null && nearby && !saturated) {
            unit.unit.gather(mineralPatch)
        } else throw NotExecutableException()
    }

    override fun toString(): String {
        return " MineMinerals"
    }
}