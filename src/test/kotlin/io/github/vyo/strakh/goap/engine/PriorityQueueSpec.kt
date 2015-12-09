package io.github.vyo.strakh.goap.engine

import io.github.vyo.strakh.goap.component.Plan
import io.github.vyo.twig.logger.Level
import io.github.vyo.twig.logger.Logger
import org.jetbrains.spek.api.Spek
import kotlin.test.assertTrue

/**
 * Created by Manuel Weidmann on 09.12.2015.
 */
class PriorityQueueSpec : Spek() {

    init {
        Logger.global.level = Level.WARN

        given("an empty priority queue") {

            var priorityQueue: Executor.PriorityQueue = Executor.PriorityQueue()

            beforeOn {
                priorityQueue = Executor.PriorityQueue()
            }

            on("adding elements with priority 1, 2, 3, 4, 5") {

                for (i in 1..5) {
                    priorityQueue.add(Executor.PriorityQueue.Element(priority = i, plan = Plan()))
                }

                it("should contain those elements in the same order") {
                    for (i in 0..4) {
                        assertTrue { priorityQueue[i].priority == i + 1 }
                    }
                }
            }

            on("adding elements with priority 5, 4, 3, 2, 1") {

                for (i in 5..1) {
                    priorityQueue.add(Executor.PriorityQueue.Element(priority = i, plan = Plan()))
                }

                it("should contain those elements in reverse order") {
                    for (i in 0..4) {
                        assertTrue { priorityQueue[i].priority == i + 1 }
                    }
                }
            }
        }

        given("a priority queue filled sequentially from 1 to 10") {

            var priorityQueue: Executor.PriorityQueue = Executor.PriorityQueue()

            beforeOn {
                priorityQueue = Executor.PriorityQueue()
                for (i in 1..10) {
                    priorityQueue.add(Executor.PriorityQueue.Element(priority = i, plan = Plan()))
                }
            }

            on("adding an element with priority -1") {

                val element: Executor.PriorityQueue.Element = Executor.PriorityQueue.Element(priority = -1, plan =
                Plan())
                priorityQueue.add(element)

                it("should occupy first spot") {
                    assertTrue { priorityQueue[0].equals(element) }
                }
            }

            on("adding an element with priority 11") {

                val element: Executor.PriorityQueue.Element = Executor.PriorityQueue.Element(priority = 11, plan =
                Plan())
                priorityQueue.add(element)

                it("should occupy the last spot") {
                    assertTrue { priorityQueue.last().equals(element) }
                }
            }

            on("adding an element with priority 4") {

                val element: Executor.PriorityQueue.Element = Executor.PriorityQueue.Element(priority = 4, plan =
                Plan())
                priorityQueue.add(element)

                it("should occupy fourth or fifth spot") {
                    assertTrue { priorityQueue[3].equals(element) or priorityQueue[4].equals(element) }
                }
            }
        }
    }
}