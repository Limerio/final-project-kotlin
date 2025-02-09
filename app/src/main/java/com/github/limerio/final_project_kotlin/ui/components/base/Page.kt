package com.github.limerio.final_project_kotlin.ui.components.base

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable

@Composable
fun Page(
    errorMessage: String?,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable @UiComposable () -> Unit
) {
    AnimatedVisibility(visible = errorMessage !== null) {
        errorMessage?.let { ErrorComponent(it) }
    }
    AnimatedVisibility(visible = isLoading) {
        CircularProgressIndicator()
    }

    AnimatedVisibility(visible = !isLoading) {
        Row(modifier = modifier) {
            content()
        }
    }
}