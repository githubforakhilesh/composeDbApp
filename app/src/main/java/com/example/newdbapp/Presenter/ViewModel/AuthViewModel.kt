package com.example.newdbapp.Presenter.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newdbapp.Domain.Model.ResultModel
import com.example.newdbapp.Domain.UseCase.AuthUseCase
import com.example.newdbapp.Utility.PreferenceManager
import com.example.newdbapp.Utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(val preferenceManager: PreferenceManager,val useCase: AuthUseCase) : ViewModel() {

        private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
        val authState: StateFlow<AuthState> = _authState.asStateFlow()



    fun checkLoginStatus() {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            // 2. Read the saved username and password asynchronously using .first()
            val savedUserName = preferenceManager.userName.first()
            val savedPassword = preferenceManager.password.first()

            // If credentials don't exist, skip the network check entirely
            if (savedUserName.isBlank() || savedPassword.isBlank()) {
                _authState.value = AuthState.Unauthenticated("No credentials found")
                return@launch
            }

            // 3. Construct your map internally
            val params = mutableMapOf<String, Any>()
            params["username"] = savedUserName
            params["password"] = savedPassword

            // 4. Trigger the repository call
            when (val result = useCase.invoke(params)) {
                is Resource.Error -> {
                    _authState.value = AuthState.Unauthenticated(result.message ?: "Unknown Error")
                }
                Resource.Loading -> {
                    _authState.value = AuthState.Loading
                }
                is Resource.Success -> {
                    _authState.value = AuthState.Authenticated(result.data.toDomain())
                }
            }

        }
     }
    }

    sealed class AuthState() {
        object Loading : AuthState()
        data class Authenticated(val resultModel: ResultModel) : AuthState()
        data class Unauthenticated(val message:String) : AuthState()
    }

