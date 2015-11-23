package io.github.vyo.strakh.goap.client

/**
 * Created by Manuel Weidmann on 21.11.2015.
 */

abstract class Action(agent: Agent) : Comparable<Action> {
    // costs
    var minerals: Int = 0
    var gas: Int = 0
    var supply: Int = 0
    var time: Int = 0
    var actions: Int = 0

    open val agent: Agent = agent
    abstract fun applicable(): Boolean
    abstract fun apply()
    abstract fun execute()

    override fun compareTo(other: Action): Int {
        return this.actions.compareTo(other.actions)
    }
}