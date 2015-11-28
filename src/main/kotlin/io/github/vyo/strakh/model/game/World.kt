package io.github.vyo.strakh.model.game

import bwapi.Game
import bwapi.Mirror

/**
 * Created by Manuel Weidmann on 22.11.2015.
 */

object World : State() {

    object meta {
        lateinit var mirror: Mirror
        lateinit var game: Game
        var mapAnalysed: Boolean = false
    }


    override fun update() {
        Units.update()
        Resources.update()
    }


}