package com.example.core.domain.model

import com.example.core.util.GAIN_WEIGHT
import com.example.core.util.KEEP_WEIGHT
import com.example.core.util.LOSE_WEIGHT

sealed class GoalType(val name: String) {
    object LoseWeight : GoalType(LOSE_WEIGHT)
    object KeepWeight : GoalType(KEEP_WEIGHT)
    object GainWeight : GoalType(GAIN_WEIGHT)

    companion object {
        fun fromString(name: String): GoalType {
            return when (name) {
                LOSE_WEIGHT -> LoseWeight
                KEEP_WEIGHT -> KeepWeight
                GAIN_WEIGHT -> GainWeight
                else -> KeepWeight
            }
        }
    }
}