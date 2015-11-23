package io.github.vyo.strakh.goap.engine

import io.github.vyo.strakh.goap.client.Action

/**
 * Created by Manuel Weidmann on 21.11.2015.
 */

object Executor {
    fun executePlan(plan: List<Action>) {
        for (action in plan) action.execute()
    }
}