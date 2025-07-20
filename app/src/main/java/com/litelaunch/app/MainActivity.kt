package com.litelaunch.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.litelaunch.app.auth.AuthViewModel
import com.litelaunch.app.ui.screens.HomeScreen
import com.litelaunch.app.ui.screens.LoginScreen
import com.litelaunch.app.ui.screens.RegisterScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val viewModel = remember { AuthViewModel() }
            val loginState by viewModel.loginState.collectAsState()

            // Navigate to home screen after login/register
            LaunchedEffect(loginState) {
                if (loginState == "success" || loginState == "registered") {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            }

            NavHost(navController = navController, startDestination = "login") {
                composable("login") {
                    LoginScreen(
                        viewModel = viewModel,
                        onNavigateToHome = {
                            navController.navigate("home") {
                                popUpTo("login") {
                                    inclusive = true
                                } // Optional: remove login from backstack
                            }
                        },
                        onNavigateToRegister = {
                            navController.navigate("register")
                        }
                    )
                }
                composable("register") {
                    RegisterScreen(
                        viewModel = viewModel,
                        onNavigateToLogin = {
                            navController.popBackStack() // go back to login screen
                        }
                    )
                }
                composable("home") {
                    HomeScreen()
                }
            }

        }
    }
}
