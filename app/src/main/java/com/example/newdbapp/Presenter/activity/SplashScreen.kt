package com.example.newdbapp.Presenter.activity

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newdbapp.R
import com.example.newdbapp.Presenter.ViewModel.AuthState
import com.example.newdbapp.Presenter.ViewModel.AuthViewModel
import com.example.newdbapp.Utility.PreferenceManager

@Composable
fun SplashScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    val authState by viewModel.authState.collectAsState()

    // 2. Trigger the network/database check ONCE when the screen enters the composition
    LaunchedEffect(Unit) {
        viewModel.checkLoginStatus()
    }

    // 3. React to state changes to navigate away
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> onNavigateToHome()
            is AuthState.Unauthenticated -> onNavigateToLogin()
            AuthState.Loading -> { /* Do nothing, stay on splash */ }
        }
    }

    // Splash UI
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.mipmap.ic_launcher_logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(120.dp),
            tint = Color.Unspecified
        )
    }
}