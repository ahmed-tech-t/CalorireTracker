package com.example.onboarding_presentation.welcome


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.core_ui.LocalSpacing
import com.example.core.R

@Preview
@Composable
fun Welcome() {
    val spacing = LocalSpacing.current
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(spacing.spaceMedium)
    ) {
        Text(
            text = stringResource(id = R.string.welcome_text) , fontSize = 18.sp
        )
    }
}