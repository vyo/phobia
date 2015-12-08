package io.github.vyo.strakh.model.game

import bwapi.Player

/**
 * Created by Manuel Weidmann on 23.11.2015.
 */

object Players : State {
    override val mutable: Boolean = false

    override fun getMutableCopy(): State {
        throw UnsupportedOperationException()
    }

    lateinit var self: Player
    lateinit var enemies: MutableList<Player>

    override fun update() {
    }
}