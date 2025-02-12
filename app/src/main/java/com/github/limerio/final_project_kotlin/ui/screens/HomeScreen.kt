package com.github.limerio.final_project_kotlin.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.limerio.final_project_kotlin.R
import com.github.limerio.final_project_kotlin.ui.components.base.Page
import com.github.limerio.final_project_kotlin.ui.components.posts.Post
import com.github.limerio.final_project_kotlin.utils.Routes
import com.github.limerio.final_project_kotlin.viewmodel.PostsViewModel
import com.github.limerio.final_project_kotlin.viewmodel.UsersViewModel

fun onClickUser(navController: NavController, userId: Int) {
    navController.navigate(Routes.UserScreen.withId(userId))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    postsViewModel: PostsViewModel,
    usersViewModel: UsersViewModel,
    modifier: Modifier = Modifier
) {
    val isLoading by postsViewModel.isLoading.collectAsState()
    val errorMessage by postsViewModel.error.collectAsState()
    val posts by postsViewModel.dataList.collectAsState()
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var postContent by remember { mutableStateOf("") }

    Page(
        errorMessage = errorMessage, isLoading = isLoading, modifier = modifier
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            items(posts.size) {
                Post(
                    data = posts[it],
                    user = usersViewModel.findById(posts[it].userId),
                    onClick = { onClickUser(navController, posts[it].userId) }
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            FloatingActionButton(
                onClick = {
                    showBottomSheet = true
                },
            ) {
                Icon(
                    Icons.Filled.Add,
                    stringResource(R.string.create_a_post_floatting_button_description)
                )
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                Column(
                    modifier = Modifier.padding(5.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = stringResource(R.string.create_new_post),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Column {
                        TextField(
                            value = postContent,
                            onValueChange = { postContent = it },
                            label = { Text(stringResource(R.string.message)) }
                        )

                        Button(onClick = { }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Send,
                                contentDescription = stringResource(R.string.send_icon)
                            )
                            Text(text = stringResource(R.string.send))
                        }
                    }
                }
            }
        }
    }
}