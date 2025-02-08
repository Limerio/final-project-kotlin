package com.github.limerio.final_project_kotlin.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.limerio.final_project_kotlin.ui.screens.HomeScreen
import com.github.limerio.final_project_kotlin.viewmodel.PostsViewModel
import com.github.limerio.final_project_kotlin.viewmodel.UsersViewModel

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun Router(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            val postsViewModel = PostsViewModel()
            val usersViewModel = UsersViewModel()
            postsViewModel.load()
            usersViewModel.load()
            HomeScreen(navController, postsViewModel, usersViewModel)
        }
    }
}