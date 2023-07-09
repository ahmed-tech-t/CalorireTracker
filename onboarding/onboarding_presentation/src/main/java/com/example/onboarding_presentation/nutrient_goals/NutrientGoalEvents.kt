package com.example.onboarding_presentation.nutrient_goals

sealed class NutrientGoalEvents {
    data class OnCarpEnter(val value: String) : NutrientGoalEvents()
    data class OnProteinEnter(val value: String) : NutrientGoalEvents()
    data class OnFatEnter(val value: String) : NutrientGoalEvents()
    object OnNextClicked : NutrientGoalEvents()
}