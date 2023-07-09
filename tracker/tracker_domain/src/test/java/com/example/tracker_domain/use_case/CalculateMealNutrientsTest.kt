package com.example.tracker_domain.use_case

import android.annotation.SuppressLint
import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.model.UserInfo
import com.example.core.domain.preferences.Preferences
import com.example.tracker_domain.models.MealType
import com.example.tracker_domain.models.TrackedFood
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import kotlin.random.Random

class CalculateMealNutrientsTest {

    lateinit var calculateMealNutrients: CalculateMealNutrients

    @Before
    fun setup() {
        val preferences = mockk<Preferences>(relaxed = true)
        every { preferences.loadUserInfo() } returns UserInfo(
            age = 20,
            activityLevel = ActivityLevel.Medium,
            carbRatio = 40f,
            fatRatio = 30f,
            gender = Gender.Male,
            goalType = GoalType.KeepWeight,
            height = 180,
            proteinRatio = 30f,
            weight = 120f
        )
        calculateMealNutrients = CalculateMealNutrients(preferences)
    }

    @SuppressLint("CheckResult")
    @Test
    fun `Calories for breakfast properly calculated`(){
        val trackedFood = (1..30).map {
            TrackedFood(
                mealType = MealType.fromString(
                    listOf(
                        "breakfast", "lunch", "dinner", "snake"
                    ).random()
                ),
                calories = Random.nextInt(2000),
                imageUrl = null,
                name = "name",
                carbs = Random.nextInt(100),
                fat = Random.nextInt(100),
                protein = Random.nextInt(100),
                amount = 100,
                date = LocalDate.now()
            )
        }
        val result = calculateMealNutrients(trackedFood)

        val breakfastCalories =
            trackedFood
                .filter { it.mealType is MealType.Breakfast }
                .sumOf { it.calories }
        val expectedCalorieForBreakFast =
            result.mealNutrients.values.filter { it.mealType is MealType.Breakfast }
                .sumOf { it.calories }

        assertThat(breakfastCalories).isEqualTo(expectedCalorieForBreakFast)
    }

}


