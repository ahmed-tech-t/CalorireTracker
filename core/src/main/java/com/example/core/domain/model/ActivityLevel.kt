package com.example.core.domain.model

import com.example.core.util.HIGH
import com.example.core.util.LOW
import com.example.core.util.MEDIUM

sealed class ActivityLevel(val name: String) {
    object Low: ActivityLevel(LOW)
    object Medium: ActivityLevel(MEDIUM)
    object High: ActivityLevel(HIGH)

    companion object {
        fun fromString(name: String): ActivityLevel {
            return when(name) {
                LOW -> Low
                MEDIUM -> Medium
               HIGH -> High
                else -> Medium
            }
        }
    }
}