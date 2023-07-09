package com.example.tracker_presentation.tracker_overview.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_ui.CarbColor
import com.example.core_ui.FatColor
import com.example.core_ui.ProteinColor
import com.example.core.R
import com.example.core_ui.LocalSpacing
import com.example.tracker_presentation.components.NutrientsBar
import com.example.tracker_presentation.components.UnitDisplay
import com.example.tracker_presentation.tracker_overview.TrackerOverviewState

@Composable
fun NutrientHeader(
    modifier: Modifier = Modifier,
    state: TrackerOverviewState
) {
    val caloriesState = animateIntAsState(targetValue = state.totalCalories)
    val spacing = LocalSpacing.current
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 50.dp, bottomStart = 50.dp))
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(vertical = spacing.spaceLarge, horizontal = spacing.spaceLarge)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween , modifier = Modifier.fillMaxWidth()) {
            UnitDisplay(value = caloriesState.value, unit = stringResource(id = R.string.kcal) , color = Color.White)
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.your_goal),
                    fontWeight = FontWeight.Light,
                    fontSize = 15.sp,
                    color = Color.White
                )
                UnitDisplay(value = state.caloriesGoal, unit = stringResource(id = R.string.kcal), color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        NutrientsBar(
            carbs = state.totalCarbs,
            protein = state.totalProtein,
            fat = state.totalFat,
            calories = state.totalCalories,
            calorieGoal = state.caloriesGoal ,
            modifier = modifier.fillMaxWidth().height(30.dp)
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween ,
            modifier = Modifier.fillMaxWidth()
        ) {
            NutrientBarInfo(
                target = state.carbsGoal,
                value = state.totalCarbs,
                name = stringResource(id = R.string.carbs),
                strokeColor = CarbColor ,
                delay = 200 ,
                modifier = Modifier.size(90.dp)
            )
            NutrientBarInfo(
                target = state.proteinGoal,
                value = state.totalProtein,
                name = stringResource(id = R.string.protein),
                strokeColor = ProteinColor,
                delay = 800 ,
                modifier = Modifier.size(90.dp)
            )
            NutrientBarInfo(
                target = state.fatGoal,
                value = state.totalFat,
                name = stringResource(id = R.string.fat),
                strokeColor = FatColor,
                delay = 1200 ,
                modifier = Modifier.size(90.dp)
            )
        }
    }
}