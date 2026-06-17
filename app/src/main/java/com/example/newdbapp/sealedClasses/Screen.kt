package com.example.newdbapp.sealedClasses

import kotlinx.serialization.Serializable
@Serializable
sealed interface Screen {
    @Serializable
    object Splash : Screen
    @Serializable
    object Login : Screen
    @Serializable
    object Signup : Screen
    @Serializable
    object Home : Screen
}