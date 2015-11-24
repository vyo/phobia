package io.github.vyo.strakh.model.action

import io.github.vyo.strakh.goap.components.Action
import io.github.vyo.strakh.goap.components.NotExecutableException
import io.github.vyo.strakh.log.Logger
import io.github.vyo.strakh.model.agent.Worker
import io.github.vyo.strakh.model.game.Resources

/**
 * Created by Manuel Weidmann on 22.11.2015.
 */

class MineMinerals(agent: Worker) : Action(agent) {
    init {
        actions = 1
    }

    val logger: Logger = Logger(this)

    override fun applicable(): Boolean {
        val (patch, nearby, saturated) = Resources.nearestMineralPatch((agent as Worker).unit)
        logger.debug("Resource spot: $patch, $nearby, $saturated")

        return patch != null && nearby && !saturated
    }

    override fun apply() {
        (agent as Worker).isMiningMinerals = true
    }

    override fun execute() {
        val (mineralPatch, nearby, saturated) = Resources.nearestMineralPatch((agent as Worker).unit)
        if (mineralPatch != null && nearby && !saturated) {
            agent.unit.gather(mineralPatch)
        } else throw NotExecutableException()
    }

    override fun toString(): String {
        return "MineMinerals"
    }
}