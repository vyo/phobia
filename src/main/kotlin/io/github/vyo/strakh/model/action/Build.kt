package io.github.vyo.strakh.model.action

import io.github.vyo.strakh.goap.client.Action
import io.github.vyo.strakh.goap.client.Agent

/**
 * Created by Manuel Weidmann on 22.11.2015.
 */

class Build(agent: Agent) : Action(agent) {
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
        return "Build"
    }
}