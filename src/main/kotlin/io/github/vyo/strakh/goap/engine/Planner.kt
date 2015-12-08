package io.github.vyo.strakh.goap.engine

import io.github.vyo.strakh.goap.component.Action
import io.github.vyo.strakh.goap.component.Agent
import io.github.vyo.twig.logger.Logger

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

        logger.debug("Formulating plan", Pair("agent", agent))
        logger.debug("with goals: $goals", Pair("agent", agent))
        logger.debug("and actions: $actions", Pair("agent", agent))

        for (goal in goals) {
            var available: MutableList<Action> = actions
            //            available.sort()

            val iterator: Iterator<Action> = available.iterator()
            while (iterator.hasNext()) {
                var action = iterator.next()
                logger.trace("Next action to check: $action", Pair("agent", agent))
                if (action.applicable()) {
                    logger.trace("Applying action", Pair("agent", agent))
                    action.apply()
                    plan.add(action)
                }
                if (goal.reached()) {
                    logger.debug("Plan found for: $goal", Pair("agent", agent))
                    logger.debug("The plan is: $plan", Pair("agent", agent))
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