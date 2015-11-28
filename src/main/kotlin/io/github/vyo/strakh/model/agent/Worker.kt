package io.github.vyo.strakh.model.agent

import io.github.vyo.strakh.goap.component.Action
import io.github.vyo.strakh.goap.component.Goal
import io.github.vyo.strakh.model.action.*
import io.github.vyo.strakh.model.goal.MiningGas
import io.github.vyo.strakh.model.goal.MiningMinerals

/**
 * Created by Manuel Weidmann on 22.11.2015.
 */

class Worker(unit: bwapi.Unit) : Unit(unit) {

    override var actions: MutableList<Action> = arrayListOf(Attack(this), Build(this), Move(this), Repair(this), MineGas
    (this), MineMinerals(this))
    override var goals: MutableList<Goal> = arrayListOf(MiningGas(this), MiningMinerals(this))


}