package io.github.vyo.strakh.model.action

import io.github.vyo.strakh.goap.component.Action
import io.github.vyo.strakh.goap.component.Agent
import io.github.vyo.strakh.model.agent.Unit
import io.github.vyo.strakh.utility.Cost

/**
 * Created by Manuel Weidmann on 22.11.2015.
 */

class Repair(val unit: Unit) : Action {

    override var agent: Agent = unit
    override var cost: Cost = Cost(actions = 1, time = 250)

    override fun applicable(): Boolean {
        return false
    }

    override fun apply() {
    }

    override fun executable(): Boolean {
        return false
    }

    override fun execute() {
    }

    override fun toString(): String {
        return "Repair"
    }
}