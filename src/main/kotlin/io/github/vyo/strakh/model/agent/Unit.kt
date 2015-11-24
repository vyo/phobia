package io.github.vyo.strakh.model.agent

import io.github.vyo.strakh.goap.components.Agent

/**
 * Created by Manuel Weidmann on 22.11.2015.
 */

abstract class Unit(unit: bwapi.Unit) : Agent() {

    var unit = unit
    var position = bwapi.Position(-1, -1)

}