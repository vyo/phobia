package io.github.vyo.strakh.model.game

/**
 * Created by Manuel Weidmann on 23.11.2015.
 */

object Agents : State {
    override val mutable: Boolean = false

    override fun update() {
    }

    //TODO: return a proper mutable copy
    override fun getMutableCopy(): State {
        return this
    }
}