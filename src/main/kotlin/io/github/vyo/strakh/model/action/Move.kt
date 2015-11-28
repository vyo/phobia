package io.github.vyo.strakh.model.action

import io.github.vyo.strakh.goap.component.Action
import io.github.vyo.strakh.model.agent.Unit

/**
 * Created by Manuel Weidmann on 22.11.2015.
 */

class Move(val unit: Unit) : Action {

    override var minerals: Int = 0
    override var gas: Int = 0
    override var supply: Int = 0
    override var larvae: Int = 0
    override var time: Int = 0
    override var actions: Int = 1

    override fun applicable(): Boolean {
        return false
    }

    override fun apply() {
    }

    override fun execute() {
    }

    override fun toString(): String {
        return "Move"
    }
}