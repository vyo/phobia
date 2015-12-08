package io.github.vyo.strakh.model.agent

import io.github.vyo.strakh.goap.component.Action
import io.github.vyo.strakh.goap.component.Goal
import io.github.vyo.strakh.goap.component.Plan
import io.github.vyo.strakh.model.action.Attack
import io.github.vyo.strakh.model.action.Move
import io.github.vyo.strakh.model.action.Repair
import io.github.vyo.strakh.model.action.unit.worker.Build
import io.github.vyo.strakh.model.action.unit.worker.MineGas
import io.github.vyo.strakh.model.action.unit.worker.MineMinerals
import io.github.vyo.strakh.model.game.Resources
import io.github.vyo.strakh.model.game.State
import io.github.vyo.strakh.model.goal.MiningGas
import io.github.vyo.strakh.model.goal.MiningMinerals
import io.github.vyo.strakh.utility.extension.getProducedUnitTypes

/**
 * Created by Manuel Weidmann on 22.11.2015.
 */

class Worker(unit: bwapi.Unit) : Unit(unit) {

    override var actions: MutableList<Action> = arrayListOf(Attack(this), Move(this), Repair(this), MineGas(this), MineMinerals(this))
    override var goals: MutableList<Goal> = arrayListOf(MiningGas(this), MiningMinerals(this))
    override var planState: State = Resources.getMutableCopy()
    override var plan: Plan = Plan()

    init {
        //add all production options available as actions
        unit.type.getProducedUnitTypes().map { actions.add(Build(this, it)) }
    }

    override fun update() {
        super.update()
        //update all attack, repair and mining actions
    }

}