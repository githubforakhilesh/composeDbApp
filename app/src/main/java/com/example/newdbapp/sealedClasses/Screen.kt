package com.example.newdbapp.sealedClasses

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {
    @Serializable
    object Splash : Screen

    @Serializable
    data class Login(
        val username: String? = null
    ) : Screen



    @Serializable
    object Signup : Screen

    @Serializable
    object Home : Screen

    @Serializable
    object Register : Screen

    @Serializable
    object CheckIn : Screen
}
