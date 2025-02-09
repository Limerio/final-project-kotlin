package com.github.limerio.final_project_kotlin.utils

sealed class Routes(val route: String) {
    data object HomeScreen : Routes("home")

    data object UserScreen : Routes("users/{id}") {
        fun withId(id: Int) = "users/$id"
    }
}