package io.github.vyo.strakh.goap.component

/**
 * Created by Manuel Weidmann on 28.11.2015.
 */
data class Cost(var health: Int = 0,
                var shield: Int = 0,
                var energy: Int = 0,
                var minerals: Int = 0,
                var gas: Int = 0,
                var supply: Int = 0,
                var larvae: Int = 0,
                var time: Int = 0,
                var actions: Int = 0) : Comparable<Cost> {

    override fun compareTo(other: Cost): Int {
        return time.compareTo(other.time)
    }

    fun plus(cost: Cost): Cost {
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