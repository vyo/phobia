package io.github.vyo.strakh.model.game

/**
 * Created by Manuel Weidmann on 23.11.2015.
 */

interface State {

    val mutable: Boolean

    fun update()

    fun getMutableCopy(): State
}