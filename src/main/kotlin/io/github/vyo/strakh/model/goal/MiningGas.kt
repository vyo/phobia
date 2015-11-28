package io.github.vyo.strakh.model.goal

import io.github.vyo.strakh.goap.component.Goal
import io.github.vyo.strakh.model.agent.Unit

/**
 * Created by Manuel Weidmann on 22.11.2015.
 */

class MiningGas(val unit: Unit) : Goal {

    override var value: Int = 15

    override fun reached(): Boolean {
        return unit.isGatheringGas
    }


    override fun toString(): String {
        return "MiningGas"
    }
}