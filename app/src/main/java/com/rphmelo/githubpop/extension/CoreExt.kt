package com.rphmelo.githubpop.extension

fun Any?.orFalse(): Boolean {
    return try {
        this as Boolean
    } catch (e: Exception) {
        return false
    }
}

fun Int?.orZero(): Int = this ?: 0
fun Double?.orZero(): Double = this ?: 0.0
fun Long?.orZero(): Long = this ?: 0