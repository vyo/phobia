package io.github.vyo.strakh.model.game

/**
 * Created by Manuel Weidmann on 22.11.2015.
 */

object World : State() {

    override fun update() {
        Units.update()
        Resources.update()
    }


}