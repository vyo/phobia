package io.github.vyo.strakh.model.game

/**
 * Created by Manuel Weidmann on 23.11.2015.
 */

object Agents : State {
    override val mutable: Boolean = false

    override fun getMutableCopy(): State {
        throw UnsupportedOperationException()
    }

    override fun update() {
    }
}