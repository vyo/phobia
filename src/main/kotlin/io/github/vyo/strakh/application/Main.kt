package io.github.vyo.strakh.application

import nl.komponents.kovenant.Kovenant
import nl.komponents.kovenant.disruptor.queue.disruptorWorkQueue

/**
 * Created by Manuel Weidmann on 22.11.2015.
 */
public fun main(args: Array<String>) {
    Kovenant.context {
        callbackContext {
            dispatcher {
                concurrentTasks = Runtime.getRuntime().availableProcessors()
                workQueue = disruptorWorkQueue()
            }
        }
    }
    Strakh.start()
}
