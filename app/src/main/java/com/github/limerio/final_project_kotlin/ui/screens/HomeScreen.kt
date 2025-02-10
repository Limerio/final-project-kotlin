package com.github.limerio.final_project_kotlin.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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

    Page(
        errorMessage = errorMessage, isLoading = isLoading, modifier = modifier
    ) {
        PullToRefreshBox(
            isRefreshing = isLoading,
            onRefresh = {
                usersViewModel.load()
            },
        ) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                items(posts.size) {
                    Post(
                        navController,
                        data = posts[it],
                        user = usersViewModel.findById(posts[it].userId),
                        onClick = { onClickUser(navController, posts[it].userId) }
                    )
                }
            }
        }

//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(20.dp),
//            verticalArrangement = Arrangement.Bottom,
//            horizontalAlignment = Alignment.End
//        ) {
//            FloatingActionButton(
//                onClick = { },
//            ) {
//                Icon(
//                    Icons.Filled.Add,
//                    stringResource(R.string.create_a_post_floatting_button_description)
//                )
//            }
//        }
    }
}