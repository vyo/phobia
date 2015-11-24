package io.github.vyo.strakh.log

import nl.komponents.kovenant.async

/**
 * Created by Manuel Weidmann on 24.11.2015.
 */

class Logger(caller: Any) {

    val caller: Any = caller

    var threshold: Level = Level.INFO

    fun log(message: String, level: Level) {
        if (level < threshold) return
        val thread: Thread = kotlin.concurrent.currentThread
        async {
            println("{${ escape("thread") }:${ escape(thread) }" +
                    "${escape("time")}:${escape(System.currentTimeMillis())}," +
                    "${escape("level")}:${escape(level.toInt())}," +
                    "${ escape("name") }:${ escape(caller) }, " +
                    "${escape("message")}:${escape(message)}}")
        }
    }

    private fun escape(string: Any): String {
        return "\"${string.toString()}\""
    }

    fun trace(message: String) {
        log(message, Level.TRACE)
    }

    fun debug(message: String) {
        log(message, Level.DEBUG)
    }

    fun info(message: String) {
        log(message, Level.INFO)
    }

    fun warn(message: String) {
        log(message, Level.WARN)
    }

    fun error(message: String) {
        log(message, Level.ERROR)
    }

    fun fatal(message: String) {
        log(message, Level.FATAL)
    }
}