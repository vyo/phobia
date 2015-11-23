package io.github.vyo.strakh.model.action

import io.github.vyo.strakh.goap.client.Action
import io.github.vyo.strakh.model.agent.Worker
import io.github.vyo.strakh.model.game.World

/**
 * Created by Manuel Weidmann on 22.11.2015.
 */

class MineGas(worker: Worker) : Action(worker) {
    init {
        actions = 1
    }

    val worker = worker

    override fun applicable(): Boolean {
        return World.freeRefineryNearby(worker.unit)
    }

    override fun apply() {
        worker.isMiningGas = true
    }

    override fun execute() {
    }

    override fun toString(): String {
        return "MineGas"
    }
}