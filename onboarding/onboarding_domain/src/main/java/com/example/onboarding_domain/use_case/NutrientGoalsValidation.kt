package com.example.onboarding_domain.use_case

import com.example.core.R
import com.example.core.util.UiText

class NutrientGoalsValidation {
    operator fun invoke(
        carpState: String,
        proteinState: String,
        fatState: String
    ): Result {
        val carpNumber = carpState.toIntOrNull()
        val proteinNumber = proteinState.toIntOrNull()
        val fatNumber = fatState.toIntOrNull()
        if (carpNumber == null || proteinNumber == null || fatNumber == null) {
            return Result.Failed(UiText.StringResource(R.string.error_invalid_values))
        }

        if (carpNumber + proteinNumber + fatNumber != 100) {
            return Result.Failed(UiText.StringResource(R.string.error_not_100_percent))
        }
        return Result.Success(
            carpNumber = carpNumber / 100f,
            proteinNumber = proteinNumber / 100f,
            fatNumber = fatNumber / 100f
        )
    }

    sealed class Result {
        class Success(val carpNumber: Float, val proteinNumber: Float, val fatNumber: Float) :
            Result()

        class Failed(val message: UiText) : Result()
    }
}