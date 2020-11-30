package com.rphmelo.githubpop.feature.utils

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class StateViewDelegate(vararg state: Pair<Int, () -> Unit>) : ReadWriteProperty<Any, Int> {

    private val states = hashMapOf<Int, () -> Unit>()
    private var currentState = 0

    init {
        for (pair in state) {
            states[pair.first] = pair.second
        }
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): Int {
        return currentState
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
        currentState = value
        states[currentState]?.invoke()
    }

}