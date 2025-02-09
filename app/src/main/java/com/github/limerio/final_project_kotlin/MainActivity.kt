package com.github.limerio.final_project_kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.github.limerio.final_project_kotlin.ui.Router
import com.github.limerio.final_project_kotlin.ui.components.layout.BottomNavItem
import com.github.limerio.final_project_kotlin.ui.components.layout.BottomNavigationBar
import com.github.limerio.final_project_kotlin.ui.theme.FinalprojectkotlinTheme
import com.github.limerio.final_project_kotlin.utils.Routes

@Composable
fun BottomBar(navController: NavHostController) {
    BottomNavigationBar(
        items = listOf(
            BottomNavItem(
                name = "Home",
                route = Routes.HomeScreen.route,
                icon = Icons.Outlined.Home,
                active = Icons.Default.Home
            ),
        ),
        navController = navController,
    )
}

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinalprojectkotlinTheme {
                val navController = rememberNavController()
                Router(navController)
            }
        }
    }
}
