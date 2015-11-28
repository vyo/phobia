package io.github.vyo.strakh.goap.component

/**
 * Created by Manuel Weidmann on 28.11.2015.
 */
data class Plan(val actions: Array<Action>,
                var actualCost: Int = 0,
                var actualSteps: Int = 0) {
    lateinit var cost: Cost
    val steps: Int = actions.size

    init {
        for (action in actions) {
            cost += action.cost
        }
    }
}
