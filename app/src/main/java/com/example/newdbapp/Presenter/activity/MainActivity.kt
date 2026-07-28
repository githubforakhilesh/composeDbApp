package com.example.newdbapp.Presenter.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.newdbapp.sealedClasses.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen() // Native splash call
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.Splash) {
                composable<Screen.Splash> {
                    SplashScreen(
                        onNavigateToLogin = { username ->
                            navController.navigate(Screen.Login(username = username)) {
                               // popUpTo(Screen.Splash) { inclusive = true }
                            }

                        },
                        onNavigateToHome = {
                            navController.navigate(Screen.Home) {
                                popUpTo(Screen.Splash) { inclusive = true }
                            }
                        },
                        onNavigateToRegisterScreen = {
                            navController.navigate(Screen.Signup) {
                                popUpTo(Screen.Splash) { inclusive = true }
                            }
                        },
                        onNavigateToCheckInScreen = {
                            navController.navigate(Screen.CheckIn) {
                                popUpTo(Screen.Splash) { inclusive = true }
                            }
                        },
                    )
                }
                composable<Screen.Login> { backStackEntry ->
                    val loginScreen = backStackEntry.toRoute<Screen.Login>()
                    LoginScreen(
                        onLoginClick = { navController.navigate(Screen.Home) },
                        username = loginScreen.username
                    )
                }
                composable<Screen.Signup> {
                    SignupScreen (
                        onSubmitClick = { username ->
                            navController.navigate(Screen.Login(username = username)) {
                                popUpTo(Screen.Splash) { inclusive = true }
                            }
                        }
                    )


                }
                composable<Screen.Home> { HomeScreen() }
                composable<Screen.Register> {
                    // TODO: Create/Show RegisterScreen
                }
                composable<Screen.CheckIn> {
                    CheckInScreen(
                        onCheckInClick = {
                            navController.navigate(Screen.Home) {
                                popUpTo(Screen.CheckIn) { inclusive = true }
                            }
                        }
                    )
                }
            }
        }
    }
}
