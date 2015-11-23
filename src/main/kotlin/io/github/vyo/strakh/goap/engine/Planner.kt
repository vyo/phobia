package io.github.vyo.strakh.goap.engine

import io.github.vyo.strakh.goap.client.Action
import io.github.vyo.strakh.goap.client.Agent

/**
 * Created by Manuel Weidmann on 21.11.2015.
 */

object Planner {
    fun formulatePlan(agent: Agent): List<Action> {
        val goals = agent.goals
        goals.sort()
        val actions = agent.actions

        val plan: MutableList<Action> = arrayListOf()

        println("Formulating plan for: $agent")
        println("\twith goals: $goals")
        println("\tand actions: $actions")
        println()

        for (goal in goals) {
            var available: MutableList<Action> = actions
            available.sort()

            val iterator: Iterator<Action> = available.iterator()
            while (iterator.hasNext()) {
                var action = iterator.next()
                println("\tNext action to check: $action")
                if (action.applicable()) {
                    println("\t\tApplying action")
                    action.apply()
                    plan.add(action)
                }
                if (goal.reached()) {
                    println()
                    println("\tPlan found for: $goal")
                    println("The plan is: $plan")
                    println()
                    return plan
                }
            }

        }

        return plan
    }

    data class Plan(val actions: Array<Action>, val cost: Int, val steps: Int)

    fun returnPlan(): Plan {
        return Plan(arrayOf(), 0, 0)
    }

}