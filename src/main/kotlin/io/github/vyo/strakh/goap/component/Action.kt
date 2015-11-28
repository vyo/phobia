package io.github.vyo.strakh.goap.component

/**
 * Created by Manuel Weidmann on 21.11.2015.
 */

interface Action : Comparable<Action> {
    // costs
    var minerals: Int
    var gas: Int
    var supply: Int
    var larvae: Int
    var time: Int
    var actions: Int

    fun applicable(): Boolean
    fun apply()
    fun execute()

    override fun compareTo(other: Action): Int {
        return this.actions.compareTo(other.actions)
    }

}