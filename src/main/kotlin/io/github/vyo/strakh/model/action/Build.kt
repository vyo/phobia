package io.github.vyo.strakh.model.action

import io.github.vyo.strakh.goap.component.Action
import io.github.vyo.strakh.goap.component.Cost
import io.github.vyo.strakh.model.agent.Unit

/**
 * Created by Manuel Weidmann on 22.11.2015.
 */

class Build(val unit: Unit) : Action {

    override var cost: Cost = Cost(actions = 1)

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