package io.github.vyo.strakh.goap.component

/**
 * Created by Manuel Weidmann on 21.11.2015.
 */

interface Agent {

    var actions: MutableList<Action>
    var goals: MutableList<Goal>
}