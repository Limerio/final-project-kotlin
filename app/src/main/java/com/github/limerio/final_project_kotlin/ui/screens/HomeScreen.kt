package com.github.limerio.final_project_kotlin.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.isTraceInProgress
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.github.limerio.final_project_kotlin.R
import com.github.limerio.final_project_kotlin.ui.components.base.ErrorComponent
import com.github.limerio.final_project_kotlin.ui.components.base.Page
import com.github.limerio.final_project_kotlin.ui.components.posts.Post
import com.github.limerio.final_project_kotlin.viewmodel.PostsViewModel
import com.github.limerio.final_project_kotlin.viewmodel.UsersViewModel
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Composable
fun HomeScreen(navController: NavController, postsViewModel: PostsViewModel = viewModel<PostsViewModel>(), usersViewModel: UsersViewModel = viewModel<UsersViewModel>()) {
    val isLoading by postsViewModel.isLoading.collectAsState()
    val errorMessage by postsViewModel.error.collectAsState()
    val posts by postsViewModel.dataList.collectAsState()

    Page(errorMessage = errorMessage, isLoading = isLoading) {
//        PullToRefreshBox(
//            isRefreshing = isLoading,
//            onRefresh = onRefresh,
//        ) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                items(posts.size) {
                    Post(data = posts[it], user = usersViewModel.findById(posts[it].userId))
                }
            }
//        }

       Column( modifier = Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End) {
           FloatingActionButton(
               onClick = {  },
           ) {
               Icon(Icons.Filled.Add, stringResource(R.string.create_a_post_floatting_button_description))
           }
       }
    }
}