package com.example.newdbapp.Presenter.activity

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newdbapp.Domain.Model.TutorialModel
import com.example.newdbapp.Presenter.ViewModel.AuthViewModel
import com.example.newdbapp.R
import com.example.newdbapp.ui.theme.OrangeColor
import com.example.newdbapp.ui.theme.RedColor
import org.intellij.lang.annotations.JdkConstants

@Composable
fun SplashScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onNavigateToLogin: (username:String) -> Unit,
    onNavigateToHome: (TutorialModel?) -> Unit,
    onNavigateToRegisterScreen: () -> Unit,
    onNavigateToCheckInScreen: () -> Unit,
) {
    val authState by viewModel.authState.collectAsState()
    val context = LocalContext.current

    // 2. Trigger the network/database check ONCE when the screen enters the composition
    LaunchedEffect(Unit) {
        viewModel.checkLoginStatus()
    }

    // 3. React to state changes to navigate away
    LaunchedEffect(authState) {
        when (authState) {
            is AuthViewModel.AuthState.GoToHomeScreen -> onNavigateToHome((authState as AuthViewModel.AuthState.GoToHomeScreen).tutorialModel)
            is AuthViewModel.AuthState.GoToLoginScreen -> (authState as AuthViewModel.AuthState.GoToLoginScreen).username?.let { onNavigateToLogin(  it) }
            is AuthViewModel.AuthState.GoToRegisterScreen -> onNavigateToRegisterScreen()
            is AuthViewModel.AuthState.GoToCheckInScreen -> onNavigateToCheckInScreen()
            is AuthViewModel.AuthState.Unauthenticated -> {
                Toast.makeText(context, (authState as AuthViewModel.AuthState.Unauthenticated).message, Toast.LENGTH_SHORT).show()
               // onNavigateToLogin()
            }
            AuthViewModel.AuthState.Loading -> { /* Do nothing, stay on splash */ }
            else -> {}
        }
    }

    SplashScreenContent()
}

@Composable
fun SplashScreenContent() {
    val gradientColors = listOf(
        OrangeColor,
        RedColor
    )

    // Splash UI
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(gradientColors)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.mipmap.ic_launcher_logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.2f)), // Subtle background to highlight the circle
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.height(24.dp))
        CircularProgressIndicator(
            modifier = Modifier.size(32.dp),
            color = Color.White,       // White matches beautifully over gradients
            strokeWidth = 3.dp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreenContent()
}
