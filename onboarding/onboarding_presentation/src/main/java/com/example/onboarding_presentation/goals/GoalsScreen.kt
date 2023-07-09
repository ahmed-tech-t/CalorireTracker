package com.example.onboarding_presentation.goals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.R
import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.GoalType
import com.example.core.util.UiEvent
import com.example.core_ui.LocalSpacing
import com.example.onboarding_presentation.components.ActionButton
import com.example.onboarding_presentation.components.SelectableButton

@Composable
fun GoalsScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: GoalsVM = hiltViewModel()
){

    LaunchedEffect(key1 = true, block = {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    onNavigate(event)
                }

                else -> Unit
            }
        }
    })

    val spacing = LocalSpacing.current
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceMedium)
                .align(Alignment.Center)
        ) {
            Text(
                text = stringResource(id = R.string.your_goal),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.heightIn(spacing.spaceMedium))
            Row(
                horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()
            ) {
                SelectableButton(
                    color = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.background,
                    isSelected = viewModel.selectedGoal is GoalType.LoseWeight,
                    text = stringResource(id = R.string.lose)
                ) {
                    viewModel.selectGoal(GoalType.LoseWeight)
                }
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton(
                    color = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.background,
                    isSelected = viewModel.selectedGoal is GoalType.KeepWeight,
                    text = stringResource(id = R.string.keep)
                ) {
                    viewModel.selectGoal(GoalType.KeepWeight)
                }
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton(
                    color = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.background,
                    isSelected = viewModel.selectedGoal is GoalType.GainWeight,
                    text = stringResource(id = R.string.gain)
                ) {
                    viewModel.selectGoal(GoalType.GainWeight)
                }
            }
        }
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = viewModel::onNextClick,
            modifier = Modifier.align(
                Alignment.BottomEnd
            )
        )

    }

}