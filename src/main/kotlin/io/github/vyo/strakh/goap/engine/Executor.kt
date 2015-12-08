package io.github.vyo.strakh.goap.engine

import io.github.vyo.strakh.goap.component.NotExecutedException
import io.github.vyo.strakh.goap.component.Plan
import io.github.vyo.twig.logger.Logger

/**
 * Created by Manuel Weidmann on 21.11.2015.
 */

object Executor {

    val logger: Logger = Logger(this)
    private val priorityQueue: MutableList<Pair<Int, Plan>> = arrayListOf()

    fun processQueue() {
        for ((priority, plan) in priorityQueue) {
            if (priority == 0) {
                executePlan(plan)
            } else break
        }
    }

    private fun executePlan(plan: Plan) {
        logger.debug("Executing plan", Pair("plan", plan))
        for (action in plan.actions) {
            logger.trace("Next action to check: $action", Pair("plan", plan), Pair("agent", action.agent))
            if (action.executable()) {
                logger.trace("Executing action")
                try {
                    action.execute()
                } catch (exception: NotExecutedException) {
                    logger.error(exception.message ?: "", Pair("plan", plan), Pair("agent", action.agent), Pair("action",
                            action))
                }
            }
        }
        logger.debug("Executed plan")
    }

    fun schedule(plan: Plan) {
        priorityQueue.add(0, Pair(0, plan))
    }

    private fun prioritise(plan: Plan) {

    }

    override fun toString(): String {
        return "executor"
    }
}