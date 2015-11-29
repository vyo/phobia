package io.github.vyo.strakh.goap.engine

import io.github.vyo.strakh.goap.component.Action
import io.github.vyo.strakh.goap.component.NotExecutedException
import io.github.vyo.twig.logger.Logger

/**
 * Created by Manuel Weidmann on 21.11.2015.
 */

object Executor {

    val logger: Logger = Logger(this)
    private val queue: MutableList<List<Action>> = arrayListOf()

    private fun processQueue() {
        //        async {
        while (true) {
            for (plan in queue) {
                executePlan(plan)
            }
        }
        //        }
    }

    fun executePlan(plan: List<Action>) {
        logger.debug("Executing plan", Pair("plan", plan))
        for (action in plan) {
            logger.trace("Next action to check: $action", Pair("plan", plan), Pair("agent", action.agent))
            if (action.executable()) {
                logger.trace("Executing action")
                try {
                    action.execute()
                } catch (exception: NotExecutedException) {
                    logger.error(exception.message!!, Pair("plan", plan), Pair("agent", action.agent), Pair("action",
                            action))
                }
            }
        }
        logger.debug("Executed plan")
    }

    fun schedule(plan: List<Action>) {
        queue.add(plan)
    }

    override fun toString(): String {
        return "executor"
    }
}