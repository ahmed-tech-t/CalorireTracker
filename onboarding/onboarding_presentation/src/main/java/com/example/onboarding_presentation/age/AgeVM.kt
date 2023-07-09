package com.example.onboarding_presentation.age

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.R
import com.example.core.domain.preferences.Preferences
import com.example.core.domain.use_case.FilterOutDigit
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import com.example.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeVM @Inject constructor(
    private val repo: Preferences, private val filterOutDigit: FilterOutDigit
) : ViewModel() {
    var age by mutableStateOf("20")
        private set
    private var _uiEvent = Channel<UiEvent>()
    var uiEvent = _uiEvent.receiveAsFlow()

    fun onAgeEnter(age: String)  {
        if (age.length <= 3) {
            this.age = filterOutDigit(age)
        }
    }

    fun onNextClicked() = viewModelScope.launch {
        val ageNumber = age.toIntOrNull() ?: kotlin.run {
            _uiEvent.send(UiEvent.ShowSnackBar(UiText.StringResource(R.string.error_age_cant_be_empty)))
            return@launch
        }
        repo.saveAge(ageNumber)
        _uiEvent.send(UiEvent.Navigate(Route.HEIGHT))
    }
}