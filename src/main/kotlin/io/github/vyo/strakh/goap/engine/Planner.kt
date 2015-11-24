package io.github.vyo.strakh.goap.engine

import io.github.vyo.strakh.goap.components.Action
import io.github.vyo.strakh.goap.components.Agent
import io.github.vyo.strakh.log.Logger

/**
 * Created by Manuel Weidmann on 21.11.2015.
 */

object Planner {

    val logger: Logger = Logger(this)
    
    fun formulatePlan(agent: Agent): List<Action> {
        val goals = agent.goals
        goals.sort()
        val actions = agent.actions

        val plan: MutableList<Action> = arrayListOf()

        logger.debug("Formulating plan for: $agent")
        logger.debug("\twith goals: $goals")
        logger.debug("\tand actions: $actions")

        for (goal in goals) {
            var available: MutableList<Action> = actions
            available.sort()

            val iterator: Iterator<Action> = available.iterator()
            while (iterator.hasNext()) {
                var action = iterator.next()
                logger.debug("\tNext action to check: $action")
                if (action.applicable()) {
                    logger.debug("\t\tApplying action")
                    action.apply()
                    plan.add(action)
                }
                if (goal.reached()) {
                    logger.debug("\tPlan found for: $goal")
                    logger.debug("The plan is: $plan")
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