package com.github.limerio.final_project_kotlin.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.github.limerio.final_project_kotlin.R
import com.github.limerio.final_project_kotlin.models.User
import com.github.limerio.final_project_kotlin.ui.components.base.EmailText
import com.github.limerio.final_project_kotlin.ui.components.base.Page
import com.github.limerio.final_project_kotlin.ui.components.base.UrlText
import com.github.limerio.final_project_kotlin.ui.components.base.UserAvatar
import com.github.limerio.final_project_kotlin.ui.components.posts.Post
import com.github.limerio.final_project_kotlin.viewmodel.UsersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserTopBar(
    navController: NavHostController,
    scrollBehavior: TopAppBarScrollBehavior,
    user: User
) {
    MediumTopAppBar(
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        actions = {
            IconButton(onClick = {

            }) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = stringResource(R.string.share)
                )
            }
        },
        title = {
            Text(
                text = user.username, style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun UserScreen(
    user: User,
    usersViewModel: UsersViewModel,
    modifier: Modifier = Modifier
) {
    val userPosts = usersViewModel.userPosts.collectAsState()
    val isLoading = usersViewModel.isLoading.collectAsState()

    Page(
        modifier = modifier
            .padding(3.dp),
        isLoading = isLoading.value,
        errorMessage = null
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
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
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        EmailText(user.email)
                        UrlText(user.website)
                    }
                }
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                items(userPosts.value.size) {
                    Post(
                        data = userPosts.value[it],
                        user = user,
                        onClick = null
                    )
                }
            }
        }
    }
}