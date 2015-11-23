package io.github.vyo.strakh.model.action

import io.github.vyo.strakh.goap.client.Action
import io.github.vyo.strakh.model.agent.Worker
import io.github.vyo.strakh.model.game.World

/**
 * Created by Manuel Weidmann on 22.11.2015.
 */

class MineMinerals(worker: Worker) : Action(worker) {
    init {
        actions = 1
    }

    var worker = worker

    override fun applicable(): Boolean {
        return World.freeMineralPatchNearby(worker.unit)
    }

    override fun apply() {
        worker.isMiningMinerals = true
    }

    override fun execute() {
        worker.unit.gather(World.nearestFreeMineralPatch(worker.unit))
    }

    override fun toString(): String {
        return "MineMinerals"
    }
}