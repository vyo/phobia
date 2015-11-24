package io.github.vyo.strakh.model.action

import io.github.vyo.strakh.goap.components.Action
import io.github.vyo.strakh.goap.components.Agent

/**
 * Created by Manuel Weidmann on 22.11.2015.
 */

class Repair(agent: Agent) : Action(agent) {
    init {
        actions = 1
    }

    override fun applicable(): Boolean {
        return false
    }

    override fun apply() {
    }

    override fun execute() {
    }

    override fun toString(): String {
        return "Repair"
    }
}