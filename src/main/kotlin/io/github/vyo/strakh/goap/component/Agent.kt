package io.github.vyo.strakh.goap.component

import io.github.vyo.strakh.goap.engine.Planner
import io.github.vyo.strakh.model.game.State

/**
 * Created by Manuel Weidmann on 21.11.2015.
 */

interface Agent {

    var actions: MutableList<Action>
    var goals: MutableList<Goal>
    var planState: State
    var plan: Plan

    interface Proto : Agent {
        var agents: MutableList<Agent>

        fun synchronise()
    }

    interface Meta : Agent {
        var agents: MutableList<Agent>

        fun merge()
    }

    fun plan() {
        Planner.formulatePlan(this)
    }

    fun act() {
    }
}