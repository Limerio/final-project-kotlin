package com.github.limerio.final_project_kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.github.limerio.final_project_kotlin.ui.Router
import com.github.limerio.final_project_kotlin.ui.components.layout.BottomNavItem
import com.github.limerio.final_project_kotlin.ui.components.layout.BottomNavigationBar
import com.github.limerio.final_project_kotlin.ui.screens.HomeScreen
import com.github.limerio.final_project_kotlin.ui.theme.FinalprojectkotlinTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinalprojectkotlinTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = "Home",
                                    route = HomeScreen,
                                    icon = Icons.Default.Home
                                ),
                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    },
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Router(navController, innerPadding)
                }
            }
        }
    }
}
