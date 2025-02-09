package com.github.limerio.final_project_kotlin.ui.components.base

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.limerio.final_project_kotlin.utils.toHslColor

@Composable
fun UserAvatar(
    id: String,
    username: String,
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    onClick: (() -> Unit)?
) {
    Box(modifier
        .size(size)
        .clickable(
            onClick = { if (onClick !== null) onClick() }
        ), contentAlignment = Alignment.Center
    ) {
        val color = remember(id, username) {
            Color("$id / $username".toHslColor())
        }
        val initials = username.take(2).uppercase()
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(SolidColor(color))
        }
        Text(text = initials, style = textStyle, color = Color.White)
    }
}