package io.github.vyo.strakh.model.goal

import io.github.vyo.strakh.goap.components.Goal
import io.github.vyo.strakh.model.agent.Worker

/**
 * Created by Manuel Weidmann on 22.11.2015.
 */

class MiningMinerals(worker: Worker) : Goal(worker) {
    init {
        value = 10
    }

    var worker = worker

    override fun reached(): Boolean {
        return worker.isMiningMinerals
    }

    override fun toString(): String {
        return "MiningMinerals"
    }
}