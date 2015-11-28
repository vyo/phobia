package io.github.vyo.strakh.goap.engine

import io.github.vyo.strakh.goap.component.Action
import io.github.vyo.strakh.goap.component.Agent
import io.github.vyo.twig.Logger

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
        logger.debug("with goals: $goals")
        logger.debug("and actions: $actions")

        for (goal in goals) {
            var available: MutableList<Action> = actions
            available.sort()

            val iterator: Iterator<Action> = available.iterator()
            while (iterator.hasNext()) {
                var action = iterator.next()
                logger.debug("Next action to check: $action")
                if (action.applicable()) {
                    logger.debug("Applying action")
                    action.apply()
                    plan.add(action)
                }
                if (goal.reached()) {
                    logger.debug("Plan found for: $goal")
                    logger.debug("The plan is: $plan")
                    return plan
                }
            }

        }

        return plan
    }

    override fun toString(): String {
        return "planner"
    }

}