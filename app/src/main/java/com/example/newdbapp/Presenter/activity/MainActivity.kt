package com.example.newdbapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newdbapp.Presenter.activity.HomeScreen
import com.example.newdbapp.Presenter.activity.LoginScreen
import com.example.newdbapp.Presenter.activity.SignupScreen
import com.example.newdbapp.Presenter.activity.SplashScreen
import com.example.newdbapp.sealedClasses.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen() // Native splash call
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.Splash) {
                composable<Screen.Splash> {
                    SplashScreen(
                        onNavigateToLogin = {
                            navController.navigate(Screen.Login) {
                                popUpTo(Screen.Splash) { inclusive = true }
                            }
                        },
                        onNavigateToHome = {
                            navController.navigate(Screen.Home) {
                                popUpTo(Screen.Splash) { inclusive = true }
                            }
                        }
                    )
                }
                composable<Screen.Login> {
                    LoginScreen(onSignupClick = { navController.navigate(Screen.Signup) })
                }
                composable<Screen.Signup> { SignupScreen(onSubmitClick = {

                }) }
                composable<Screen.Home> { HomeScreen() }
            }
        }
    }
}