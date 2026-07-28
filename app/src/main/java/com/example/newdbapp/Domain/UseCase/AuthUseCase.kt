package com.example.newdbapp.Domain.UseCase

import com.example.newdbapp.Data.Dto.ResultDto
import com.example.newdbapp.Domain.Model.AppStatusModel
import com.example.newdbapp.Domain.Model.LoginModel
import com.example.newdbapp.Domain.Model.ResultModel
import com.example.newdbapp.Domain.Repository.AuthRepository
import com.example.newdbapp.Utility.ParamsConstant
import com.example.newdbapp.Utility.Resource
import javax.inject.Inject


class IsRegisterUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(params: MutableMap<String, Any>): Resource<ResultModel?> {
        val deviceId = params[ParamsConstant.DEVICE_ID]?.toString()

        if (deviceId.isNullOrBlank()) {
            return Resource.Error("Device ID is missing")
        }

        return when (val resource = authRepository.isRegister(params)) {
            is Resource.Success -> Resource.Success(resource.data?.toDomain())
            is Resource.Error -> Resource.Error(resource.message, resource.cause)
            Resource.Loading -> Resource.Loading
        }
    }
}

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(params: MutableMap<String, Any>): Resource<LoginModel?> {
        val username:String = params[ParamsConstant.USERNAME].toString()
        val password:String = params[ParamsConstant.PASSWORD].toString()
        if (username.isNullOrEmpty()) {
            return Resource.Error("Username is missing")
        }
        if (password.isNullOrEmpty()) {
            return Resource.Error("Password is missing")
        }

        return when (val resource = authRepository.login(params)) {
            is Resource.Success -> Resource.Success(resource.data?.toModel())
            is Resource.Error -> Resource.Error(resource.message, resource.cause)
            Resource.Loading -> Resource.Loading
        }
    }

}

class CheckAppStateUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(params: MutableMap<String, Any>): Resource<AppStatusModel?> {
        val dboy_id: String = params[ParamsConstant.DBOY_ID].toString()
        if (dboy_id.isBlank()) {
            return Resource.Error("Delivery boy ID is missing")
        }
        return when (val resource = authRepository.checkAppStatus(params)) {
            is Resource.Success -> Resource.Success(resource.data?.toModel())
            is Resource.Error -> Resource.Error(resource.message, resource.cause)
            Resource.Loading -> Resource.Loading
        }
    }
}






