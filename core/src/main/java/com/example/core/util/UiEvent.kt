package com.example.core.util

sealed class UiEvent {
    data class Navigate(val route: String) : UiEvent()
    object NavigationUp : UiEvent()
    data class ShowSnackBar(val text: UiText) : UiEvent()

}