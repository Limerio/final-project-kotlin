package com.github.limerio.final_project_kotlin.ui.components.posts

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.limerio.final_project_kotlin.models.Post
import com.github.limerio.final_project_kotlin.models.User
import com.github.limerio.final_project_kotlin.ui.components.base.UserAvatar


@Composable
fun Post(
    data: Post,
    user: User,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)?
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        UserAvatar(
            id = user.id.toString(),
            username = user.username,
            onClick = {
                if (onClick != null) {
                    onClick()
                }
            })

        Column {
            Text(
                text = user.username,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    if (onClick != null) {
                        onClick()
                    }
                }
            )
            Text(
                text = data.body,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.animateContentSize()
            )
        }
        Column {

        }
    }
}