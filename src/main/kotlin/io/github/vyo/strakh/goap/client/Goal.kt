package io.github.vyo.strakh.goap.client

/**
 * Created by Manuel Weidmann on 21.11.2015.
 */

abstract class Goal(agent: Agent) : Comparable<Goal> {

    var value: Int = 0

    abstract fun reached(): Boolean

    override fun compareTo(other: Goal): Int {
        return value.compareTo(other.value)
    }
}