package io.github.vyo.strakh.goap.engine

import io.github.vyo.strakh.goap.component.Action
import io.github.vyo.strakh.goap.component.NotExecutedException
import io.github.vyo.strakh.goap.component.Plan
import io.github.vyo.twig.logger.Logger

/**
 * Created by Manuel Weidmann on 21.11.2015.
 */

object Executor {

    val logger: Logger = Logger(this)
    private val priorityQueue: PriorityQueue = PriorityQueue(arrayListOf())

    fun processQueue() {
        logger.debug("processing plan queue")
        for (element in priorityQueue) {
            if (element.priority == 0) {
                processQueueElement(element)
            } else {
                element.priority--
            }
        }
    }

    private fun processQueueElement(element: PriorityQueue.Element) {
        logger.debug("Executing plan", Pair("plan", element.plan), Pair("step", Pair(element.executionIndex,
                element.plan.steps)))

        val action: Action = element.plan.actions[element.executionIndex]

        logger.trace("Next action to check: $action", Pair("plan", element.plan), Pair("agent", action.agent))

        if (!action.executable()) {
            logger.debug("Action not executable")
            return
        }

        try {
            logger.trace("Executing action")
            action.execute()
        } catch (e: NotExecutedException) {
            logger.error(e.message!!)
            return
        }

        element.executionIndex++
        element.priority = action.cost.time
        priorityQueue.add(element)

        if (element.executionIndex == element.plan.steps) {
            priorityQueue.removeRaw(element)
            logger.debug("Executed plan", Pair("plan", element.plan))
        }
    }

    fun schedule(plan: Plan) {
        priorityQueue.add(PriorityQueue.Element(plan = plan))
        logger.debug("scheduled $plan")
    }

    override fun toString(): String {
        return "executor"
    }

    class PriorityQueue(var list: MutableList<PriorityQueue.Element>) : MutableList<PriorityQueue.Element> by list {

        data class Element(var priority: Int = 0, var plan: Plan, var executionIndex: Int = 0)

        override fun add(element: Element): Boolean {
            var lowerBound: Int = 0
            var upperBound: Int = priorityQueue.size - 1

            while (lowerBound != upperBound) {
                if (element.priority < upperBound / 2) {
                    upperBound /= 2
                } else {
                    lowerBound = upperBound / 2
                }
            }

            list.removeRaw(element)
            list.add(lowerBound, element)

            logger.trace("priority index $lowerBound for ${element.plan}")

            return true
        }
    }
}