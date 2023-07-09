package com.example.onboarding_presentation.age

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.R
import com.example.core.util.UiEvent
import com.example.core_ui.LocalSpacing
import com.example.onboarding_presentation.components.ActionButton
import com.example.onboarding_presentation.components.UnitTextField

@Composable
fun AgeScreen(
    scaffoldState: SnackbarHostState,
    viewModel: AgeVM = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true, block = {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.showSnackbar(
                        message = event.text.asString(context)
                    )
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
                text = stringResource(id = R.string.whats_your_age),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.heightIn(spacing.spaceMedium))
            
            UnitTextField(value = viewModel.age, unit = stringResource(id = R.string.years), onTextChanged ={ value->
                viewModel.onAgeEnter(value)
            } )
        }
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = viewModel::onNextClicked,
            modifier = Modifier.align(
                Alignment.BottomEnd
            )
        )
    }
}