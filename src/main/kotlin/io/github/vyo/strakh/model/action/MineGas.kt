package io.github.vyo.strakh.model.action

import io.github.vyo.strakh.goap.component.Action
import io.github.vyo.strakh.goap.component.Cost
import io.github.vyo.strakh.model.agent.Unit
import io.github.vyo.strakh.model.game.Resources

/**
 * Created by Manuel Weidmann on 22.11.2015.
 */

class MineGas(val unit: Unit) : Action {

    override var cost: Cost = Cost(actions = 1)

    override fun applicable(): Boolean {
        var spot: Resources.ResourceSpot = Resources.nearestMineralPatch(unit.unit)
        val (geyser, nearby, saturated, hasRefinery) = spot

        return geyser != null && nearby && !saturated && hasRefinery
    }

    override fun apply() {
        unit.isGatheringGas = true
    }

    override fun execute() {
    }

    override fun toString(): String {
        return "MineGas"
    }
}