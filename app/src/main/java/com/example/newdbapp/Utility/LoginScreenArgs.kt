package com.example.newdbapp.Utility

import kotlinx.serialization.Serializable

@Serializable
data class LoginScreenArgs(
    val username: String?
)