package io.github.vyo.strakh.model.action

import io.github.vyo.strakh.goap.components.Action
import io.github.vyo.strakh.model.agent.Worker
import io.github.vyo.strakh.model.game.Resources

/**
 * Created by Manuel Weidmann on 22.11.2015.
 */

class MineGas(agent: Worker) : Action(agent) {
    init {
        actions = 1
    }

    override fun applicable(): Boolean {
        val (geyser, nearby, saturated, hasRefinery) = Resources.nearestMineralPatch((agent as Worker).unit)
        return geyser != null && nearby && !saturated && hasRefinery
    }

    override fun apply() {
        (agent as Worker).isMiningGas = true
    }

    override fun execute() {
    }

    override fun toString(): String {
        return "MineGas"
    }
}