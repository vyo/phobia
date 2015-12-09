package io.github.vyo.strakh.utility

/**
 * Created by Manuel Weidmann on 28.11.2015.
 *
 * [larvae] cost works like [supply] in that internal values are double the amount shown
 * in-game
 */
data class Cost(var health: Int = 0,
                var shield: Int = 0,
                var energy: Int = 0,
                var minerals: Int = 0,
                var gas: Int = 0,
                var supply: Int = 0,
                var larvae: Int = 0,
                var time: Int,
                var actions: Int) : Comparable<Cost> {

    override fun compareTo(other: Cost): Int {
        return time.compareTo(other.time)
    }

    operator fun plus(cost: Cost): Cost {
        return Cost(health = health + cost.health,
                shield = shield + cost.shield,
                energy = energy + cost.energy,
                minerals = minerals + cost.minerals,
                gas = gas + cost.gas,
                supply = cost.supply,
                larvae = cost.larvae,
                time = cost.time,
                actions = cost.actions)
    }

}