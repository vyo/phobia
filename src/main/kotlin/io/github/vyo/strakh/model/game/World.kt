package io.github.vyo.strakh.model.game

/**
 * Created by Manuel Weidmann on 22.11.2015.
 */

object World : State {
    override val mutable: Boolean = false

    override fun update() {
        Units.update()
        Resources.update()
    }

    //TODO: return a proper mutable copy
    override fun getMutableCopy(): State {
        return this
    }
}