package io.github.vyo.strakh.goap.component

import io.github.vyo.strakh.utility.Cost

/**
 * Created by Manuel Weidmann on 21.11.2015.
 */

interface Action : Comparable<Action> {

    var agent: Agent
    var cost: Cost

    fun applicable(): Boolean
    fun apply()
    fun executable(): Boolean
    fun execute()

    override fun compareTo(other: Action): Int {
        return cost.compareTo(other.cost)
    }

    interface Atomic : Action

    interface Composite : Action {
        var actions: List<Action>
    }

}