package com.github.limerio.final_project_kotlin.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.github.limerio.final_project_kotlin.ui.components.base.ErrorComponent
import com.github.limerio.final_project_kotlin.ui.components.posts.Post
import com.github.limerio.final_project_kotlin.viewmodel.PostsViewModel
import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Composable
fun HomeScreen(navController: NavController, postsViewModel: PostsViewModel = viewModel()) {
    val isSearching by postsViewModel.isLoading.collectAsState()
    val errorMessage by postsViewModel.error.collectAsState()
    val dataList by postsViewModel.dataList.collectAsState()

    AnimatedVisibility(visible = errorMessage !== null) {
        errorMessage?.let { ErrorComponent(it) }
    }
    AnimatedVisibility(visible = isSearching) {
        CircularProgressIndicator()
    }
    AnimatedVisibility(visible = !isSearching) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            items(dataList.size) {
                Post(data = dataList[it])
            }
        }
    }
}