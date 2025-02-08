package com.github.limerio.final_project_kotlin.ui.components.base

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.UiComposable

@Composable
fun Page(errorMessage: String?, isLoading: Boolean, content: @Composable @UiComposable () -> Unit) {
    AnimatedVisibility(visible = errorMessage !== null) {
        errorMessage?.let { ErrorComponent(it) }
    }
    AnimatedVisibility(visible = isLoading) {
        CircularProgressIndicator()
    }

    AnimatedVisibility(visible = !isLoading) {
        content()
    }
}