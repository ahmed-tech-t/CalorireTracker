package com.example.onboarding_presentation.nutrient_goals

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.preferences.Preferences
import com.example.core.domain.use_case.FilterOutDigit
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import com.example.onboarding_domain.use_case.NutrientGoalsValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalsVM @Inject constructor(
    private val repo: Preferences,
    private val filterOutDigit: FilterOutDigit,
    private val nutrientValidation: NutrientGoalsValidation
) : ViewModel() {

    var states by mutableStateOf(NutrientGoalsState())
        private set

    private var _uiEvent = Channel<UiEvent>()
    var uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(events: NutrientGoalEvents) {
        when (events) {
            is NutrientGoalEvents.OnCarpEnter -> {
                states = states.copy(carpState = filterOutDigit(events.value))
            }

            is NutrientGoalEvents.OnFatEnter -> {
                states = states.copy(fatState = filterOutDigit(events.value))
            }

            is NutrientGoalEvents.OnProteinEnter -> {
                states = states.copy(proteinState = filterOutDigit(events.value))
            }

            NutrientGoalEvents.OnNextClicked -> {
                val result = nutrientValidation(
                    carpState = states.carpState,
                    proteinState = states.proteinState,
                    fatState = states.fatState
                )
                when(result){
                    is NutrientGoalsValidation.Result.Failed -> {
                      viewModelScope.launch {
                          _uiEvent.send(UiEvent.ShowSnackBar(result.message))
                      }
                    }
                    is NutrientGoalsValidation.Result.Success -> {
                        repo.saveCarbRatio(result.carpNumber)
                        repo.saveProteinRatio(result.proteinNumber)
                        repo.saveFatRatio(result.fatNumber)
                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.Navigate(Route.TRACKER_OVERVIEW))
                        }
                    }
                }
            }
        }
    }
}