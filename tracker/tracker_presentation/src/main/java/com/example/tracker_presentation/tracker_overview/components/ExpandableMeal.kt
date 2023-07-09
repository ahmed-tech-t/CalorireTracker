package com.example.tracker_presentation.tracker_overview.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.core.R
import com.example.core_ui.LocalSpacing
import com.example.tracker_presentation.components.UnitDisplay
import com.example.tracker_presentation.tracker_overview.Meal


@Composable
fun ExpandableMeal(
    meal: Meal,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onToggleClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .clickable { onToggleClick() }
                .padding(spacing.spaceMedium)
        ) {
            Image(
                painter = painterResource(id = meal.drawableRes),
                contentDescription = meal.name.asString(context)
            )
            Spacer(modifier = Modifier.width(spacing.spaceMedium))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = meal.name.asString(context),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Icon(
                        imageVector = if (meal.isExpanded) Icons.Default.KeyboardArrowUp
                        else Icons.Default.KeyboardArrowDown,
                        contentDescription = meal.name.asString(context)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    UnitDisplay(
                        value = meal.carbs,
                        unit = stringResource(id = R.string.kcal),
                        valueSize = 20.sp
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {
                        NutrientInfo(
                            text = stringResource(id = R.string.carbs),
                            value = meal.carbs,
                            unit = stringResource(id = R.string.grams)
                        )
                        Spacer(modifier = Modifier.width(spacing.spaceSmall))

                        NutrientInfo(
                            text = stringResource(id = R.string.protein),
                            value = meal.protein,
                            unit = stringResource(id = R.string.grams)
                        )
                        Spacer(modifier = Modifier.width(spacing.spaceSmall))
                        NutrientInfo(
                            text = stringResource(id = R.string.fat),
                            value = meal.fat,
                            unit = stringResource(id = R.string.grams)
                        )
                    }

                }

            }
        }
        AnimatedVisibility(visible = meal.isExpanded) {
            content()
        }
    }
}