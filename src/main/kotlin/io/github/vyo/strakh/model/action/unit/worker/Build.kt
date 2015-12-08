package io.github.vyo.strakh.model.action.unit.worker

import bwapi.UnitType
import io.github.vyo.strakh.goap.component.Action
import io.github.vyo.strakh.goap.component.Agent
import io.github.vyo.strakh.model.agent.Unit
import io.github.vyo.strakh.utility.Cost
import io.github.vyo.strakh.utility.extension.getCost

/**
 * Created by Manuel Weidmann on 22.11.2015.
 */

class Build(val unit: Unit, val unitType: UnitType) : Action {

    override var agent: Agent = unit
    override var cost: Cost = unitType.getCost()

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
        return "Build ${unitType.toString()}"
    }
}