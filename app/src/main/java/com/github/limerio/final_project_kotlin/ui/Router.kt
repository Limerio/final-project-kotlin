package com.github.limerio.final_project_kotlin.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.limerio.final_project_kotlin.BottomBar
import com.github.limerio.final_project_kotlin.ui.screens.HomeScreen
import com.github.limerio.final_project_kotlin.ui.screens.UserScreen
import com.github.limerio.final_project_kotlin.ui.screens.UserTopBar
import com.github.limerio.final_project_kotlin.utils.Routes
import com.github.limerio.final_project_kotlin.viewmodel.PostsViewModel
import com.github.limerio.final_project_kotlin.viewmodel.UsersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun Router(navController: NavHostController) {
    val postsViewModel = PostsViewModel()
    val usersViewModel = UsersViewModel()
    postsViewModel.load()
    usersViewModel.load()

    NavHost(
        navController = navController,
        startDestination = Routes.HomeScreen.route
    ) {
        composable(Routes.HomeScreen.route, enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                tween(1000)
            )
        }, exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                tween(1000)
            )
        }) {
            Scaffold(
                bottomBar = { BottomBar(navController) },
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                HomeScreen(
                    navController,
                    postsViewModel,
                    usersViewModel,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        composable(
            route = Routes.UserScreen.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType }),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(1000)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(1000)
                )
            }
        ) {
            val scrollBehavior =
                TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
            val userId = it.arguments?.getInt("id") ?: return@composable

            val user = usersViewModel.findById(userId)
            usersViewModel.fillAllPosts(user.id)

            Scaffold(
                topBar = { UserTopBar(navController, scrollBehavior, user) },
                bottomBar = { BottomBar(navController) },
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
            ) { innerPadding ->
                UserScreen(
                    user,
                    usersViewModel,
                    modifier = Modifier.padding(innerPadding)
                )
            }

        }
    }
}