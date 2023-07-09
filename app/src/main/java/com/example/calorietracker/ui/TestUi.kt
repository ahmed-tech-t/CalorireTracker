package com.example.calorietracker.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import com.example.calorietracker.ui.theme.CalorieTrackerTheme
import com.example.core.R
import com.example.core.util.UiText
import com.example.tracker_domain.models.MealType
import com.example.tracker_domain.models.TrackableFood
import com.example.tracker_domain.models.TrackedFood
import com.example.tracker_presentation.search.TrackableFoodUiState
import com.example.tracker_presentation.search.components.SearchBar
import com.example.tracker_presentation.search.components.TrackableFoodItem
import com.example.tracker_presentation.tracker_overview.Meal
import com.example.tracker_presentation.tracker_overview.components.ExpandableMeal
import com.example.tracker_presentation.tracker_overview.components.TrackedFoodItem
import java.time.LocalDate


@Preview(name = "Light")
@Composable
fun TestSearch() {
    CalorieTrackerTheme {
        SearchBar(onSearch = { /*TODO*/ }, text = "",
            onTextChanged = {

            }, onFocusChanged = {

            })
    }
}


@OptIn(ExperimentalCoilApi::class)
@Preview
@Composable
fun TestTrackedFoodItem() {
    CalorieTrackerTheme {
        TrackedFoodItem(trackedFood = TrackedFood(
            name = "indomi",
            date = LocalDate.now(),
            mealType = MealType.Lunch,
            amount = 450,
            carbs = 540,
            calories = 450,
            fat = 450,
            protein = 450,
            imageUrl = "",
        ), onDeleteClick = { /*TODO*/ })
    }
}

@Preview
@Composable
fun TextExpandableMeal() {
    CalorieTrackerTheme {
        ExpandableMeal(
            meal = Meal(
                name = UiText.DynamicText("dsfsdfs sgfdsgfs sdf"),
                isExpanded = true,
                protein = 350,
                fat = 550,
                calories = 4541,
                mealType = MealType.Lunch,
                carbs = 487,
                drawableRes = R.drawable.ic_burger
            ), content = { /*TODO*/ }) {
        }
    }
}


@OptIn(ExperimentalCoilApi::class)
@Preview
@Composable
fun TestTrackableFoodItem() {
    CalorieTrackerTheme {
        TrackableFoodItem(
            trackableFoodUiState = TrackableFoodUiState(
                TrackableFood(
                    name = "noodles",
                    proteinPer100g = 40,
                    imageUrl = "",
                    fatPer100g = 44,
                    carbsPer100g = 45,
                    caloriesPer100g = 45
                ),
                isExpanded = true
            ),
            onClick = { /*TODO*/ },
            onAmountChange = {

            },
            onTrack = { /*TODO*/ })
    }
}