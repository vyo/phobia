package io.github.vyo.strakh.model.game

import bwapi.Unit

/**
 * Created by Manuel Weidmann on 23.11.2015.
 */

object Units : State {
    override val mutable: Boolean = false

    var own: MutableList<Unit> = arrayListOf()
    var enemy: MutableList<Unit> = arrayListOf()
    var neutral: MutableList<Unit> = arrayListOf()

    override fun update() {
        //update player units
        own = Players.self.units
        //update enemy units

        //update neutral units
        neutral = Meta.game.neutralUnits
    }

    //TODO: return a proper mutable copy
    override fun getMutableCopy(): State {
        return this
    }
}