package com.example.core.util

import android.content.Context

sealed class UiText {
    class DynamicText(val text: String) : UiText()
    class StringResource(val resId: Int) : UiText()

    fun asString(context: Context): String {
        return when (this) {
            is DynamicText -> text
            is StringResource -> context.getString(resId)
        }
    }
}