package com.github.limerio.final_project_kotlin.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.github.limerio.final_project_kotlin.models.User
import com.github.limerio.final_project_kotlin.repositories.UsersRepository
import com.github.limerio.final_project_kotlin.ui.components.base.Page
import com.github.limerio.final_project_kotlin.ui.components.base.UserAvatar
import com.github.limerio.final_project_kotlin.ui.components.posts.Post

@Composable
fun UserScreen(navController: NavHostController, user: User, modifier: Modifier = Modifier) {

    val userPosts = UsersRepository.findAllPosts(user.id)
    Page(modifier = modifier, isLoading = false, errorMessage = null) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                UserAvatar(
                    id = user.id.toString(),
                    username = user.username,
                    textStyle = MaterialTheme.typography.titleLarge,
                    size = 60.dp,
                    onClick = null
                )
                Text(
                    text = user.username, style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            items(userPosts.size) {
                Post(
                    navController,
                    data = userPosts[it],
                    user = user
                )
            }
        }
    }
}