package com.litelaunch.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.litelaunch.app.auth.AuthViewModel
import com.litelaunch.app.ui.screens.HomeScreen
import com.litelaunch.app.ui.screens.LoginScreen
import com.litelaunch.app.ui.screens.RegisterScreen
import com.litelaunch.app.ui.screens.SplashScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val authViewModel: AuthViewModel = viewModel()
            val navController = rememberNavController()

            val startDestination = "splash" // instead of login/home directly

            NavHost(navController = navController, startDestination = startDestination) {
                composable("splash") {
                    SplashScreen(navController, authViewModel)
                }
                composable("login") {
                    LoginScreen(navController, authViewModel)
                }
                composable("register") {
                    RegisterScreen(navController, authViewModel)
                }
                composable("home") {
                    HomeScreen(navController, authViewModel)
                }
            }
        }
    }
}
