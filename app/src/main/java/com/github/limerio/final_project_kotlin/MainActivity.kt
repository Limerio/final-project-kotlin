package com.github.limerio.final_project_kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.github.limerio.final_project_kotlin.ui.Router
import com.github.limerio.final_project_kotlin.ui.theme.FinalprojectkotlinTheme

@Composable
fun BottomBar(navController: NavHostController) {
//    BottomNavigationBar(
//        items = listOf(
//            BottomNavItem(
//                name = "Home",
//                route = Routes.HomeScreen.route,
//                icon = Icons.Outlined.Home,
//                active = Icons.Default.Home
//            ),
//        ),
//        navController = navController,
//    )
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
