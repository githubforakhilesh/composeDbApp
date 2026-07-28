package com.example.newdbapp.Presenter.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newdbapp.Domain.Model.LoginModel
import com.example.newdbapp.Domain.Model.TutorialModel
import com.example.newdbapp.Domain.UseCase.CheckAppStateUseCase
import com.example.newdbapp.Domain.UseCase.IsRegisterUseCase
import com.example.newdbapp.Domain.UseCase.LoginUseCase
import com.example.newdbapp.Domain.UseCase.RegisterDeviceUseCase
import com.example.newdbapp.Utility.ParamsConstant
import com.example.newdbapp.Utility.PreferenceManager
import com.example.newdbapp.Utility.Resource
import com.example.newdbapp.Utility.getDeviceId
import com.example.newdbapp.sealedClasses.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val preferenceManager: PreferenceManager,
    private val isRegisterUseCase: IsRegisterUseCase,
    private val loginUseCase: LoginUseCase,
    private val checkAppStateUseCase: CheckAppStateUseCase,
    private val registerUseCase: RegisterDeviceUseCase

) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    fun checkLoginStatus() {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            val savedUserName = preferenceManager.userName.first()
            val savedPassword = preferenceManager.password.first()

            if (savedUserName.isEmpty() || savedPassword.isEmpty()) {
                // No credentials, check if device is registered
                val params = mutableMapOf<String, Any>()
                params[ParamsConstant.DEVICE_ID] = getDeviceId(context)

                when (val result = isRegisterUseCase(params)) {
                    is Resource.Success -> {
                        val status = result.data?.status ?: ""
                        if (status.isEmpty() || status != ParamsConstant.SUCCESS) {
                            _authState.value = AuthState.GoToRegisterScreen
                        } else {
                            _authState.value = AuthState.GoToLoginScreen("")
                        }
                    }

                    is Resource.Error -> _authState.value =
                        AuthState.Unauthenticated(message = result.message)

                    Resource.Loading -> _authState.value = AuthState.Loading
                }
            } else {
                // Credentials exist, try to login
                val params = mutableMapOf<String, Any>()
                params["username"] = savedUserName
                params["password"] = savedPassword

                when (val result = loginUseCase(params)) {
                    is Resource.Success -> {
                        if (result.data != null) {
                            if (result.data.mUser != null) {
                                val loginId = result.data.mUser?.mId ?: 0
                                preferenceManager.saveUserNamePassword(
                                    userName = savedUserName,
                                    password = savedPassword
                                )
                                preferenceManager.saveLoginId(loginId = loginId.toString())
                                checkAppState(loginId.toString());
                            } else {
                                _authState.value = AuthState.Unauthenticated(
                                    message = result.data.mMsg ?: "Something goes wrong"
                                )
                            }

                        } else {
                            _authState.value = AuthState.Unauthenticated("Login failed")
                        }
                    }

                    is Resource.Error -> {
                        _authState.value = AuthState.Unauthenticated(result.message)
                    }

                    Resource.Loading -> _authState.value = AuthState.Loading
                }
            }
        }
    }


    private fun checkAppState(loginId: String) {
        val checkedInState: String = "CheckedIn"
        val idleState: String = "idle";
        val checkoutState: String = "CheckedOut";
        val params = mutableMapOf<String, Any>()
        params[ParamsConstant.DBOY_ID] = loginId
        viewModelScope.launch {
            when (val result = checkAppStateUseCase.invoke(params)) {
                is Resource.Error -> _authState.value =
                    AuthState.Unauthenticated(message = result.message)

                Resource.Loading -> _authState.value = AuthState.Loading
                is Resource.Success -> {
                    if (result.data != null) {
                        if (result.data.status.equals(ParamsConstant.SUCCESS)) {
                            val currentState = result.data.state?.currentState
                            val nextState = result.data.state?.nextState
                            val tutorial = result.data.tutorial
                            if (currentState.equals(idleState) && nextState.equals(checkedInState)) {
                                _authState.value = AuthState.GoToCheckInScreen
                            } else if (currentState.equals(checkedInState) || currentState.equals(
                                    checkoutState
                                )
                            ) {
                                _authState.value = AuthState.GoToHomeScreen(tutorial)
                            }
                        } else {
                            _authState.value = AuthState.Unauthenticated(
                                message = result.data.msg ?: "Something goes wrong"
                            )
                        }
                    } else {
                        _authState.value = AuthState.Unauthenticated(message = "Something goes wrong")
                    }
                }

            }
        }
    }
    fun resetAuthState() {
        _authState.value = AuthState.Idle
    }
    fun doLogin(username: String?, password: String?) {

        viewModelScope.launch {
            _authState.value = AuthState.Loading

            val params = mutableMapOf<String, Any>()
            params["username"] = username ?:""
            params["password"] = password ?: ""

            when (val result = loginUseCase(params)) {
                is Resource.Success -> {
                    if (result.data?.mUser != null) {
                        val loginId = result.data.mUser?.mId ?: 0
                        preferenceManager.saveUserNamePassword(
                            userName = username ?: "",
                            password = password ?: ""
                        )
                        preferenceManager.saveLoginId(loginId = loginId.toString())
                        checkAppState(loginId.toString())
                    } else {
                        _authState.value = AuthState.Unauthenticated(
                            message = result.data?.mMsg ?: "Login failed"
                        )
                    }
                }

                is Resource.Error -> {
                    _authState.value = AuthState.Unauthenticated(result.message)
                }

                Resource.Loading -> _authState.value = AuthState.Loading
            }
        }
    }


    fun registerDevice(userName: String) {
        val map = mutableMapOf<String, String>()
        map["deviceId"] = getDeviceId(context)
        map["mobile"] = userName
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            when (val result = registerUseCase.invoke(map)) {
                is Resource.Error -> _authState.value =
                    AuthState.Unauthenticated(message = result.message)

                Resource.Loading -> _authState.value = AuthState.Loading
                is Resource.Success -> {
                    if (result.data != null) {
                        if (result.data.status.equals(ParamsConstant.SUCCESS)) {
                            _authState.value = AuthState.GoToLoginScreen(username = userName)
                        } else {
                            _authState.value =
                                AuthState.Unauthenticated(message = result.data.message)
                        }
                    } else {
                        _authState.value = AuthState.Unauthenticated(message = "data not found")
                    }
                }
            }
        }
    }


    sealed class AuthState {
        object Idle : AuthState()
        object Loading : AuthState()

        // data class Authenticated(val loginModel: LoginModel) : AuthState()
        data class Unauthenticated(val message: String) : AuthState()
        data class GoToLoginScreen(val username: String?) : AuthState()
        object GoToRegisterScreen : AuthState()
        object GoToCheckInScreen : AuthState()
        data class GoToHomeScreen(val tutorialModel: TutorialModel?) : AuthState()

    }
}


