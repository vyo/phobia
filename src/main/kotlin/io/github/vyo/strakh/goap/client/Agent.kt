package io.github.vyo.strakh.goap.client

/**
 * Created by Manuel Weidmann on 21.11.2015.
 */

abstract class Agent {

    abstract var actions: MutableList<Action>
    abstract var goals: MutableList<Goal>
}