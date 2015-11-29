package io.github.vyo.strakh.goap.component

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

}