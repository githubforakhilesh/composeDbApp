package com.example.newdbapp.Domain.UseCase

import com.example.newdbapp.Data.Dto.ResultDto
import com.example.newdbapp.Domain.Model.ResultModel
import com.example.newdbapp.Domain.Repository.AuthRepository
import com.example.newdbapp.Utility.ParamsConstant
import com.example.newdbapp.Utility.Resource
import javax.inject.Inject

class RegisterDeviceUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(params: MutableMap<String, String>): Resource<ResultModel?> {
        val username: String = params[ParamsConstant.MOBILE].toString()
        val deviceId: String = params[ParamsConstant.DEVICE_ID].toString()
        if (username.isBlank()) {
            return Resource.Error("Device UserName is missing")
        } else if (deviceId.isBlank()) {
            return Resource.Error("Device ID is not found")
        }
        return when (val resource = authRepository.registerDevice(params)) {
            is Resource.Success -> Resource.Success(resource.data?.toDomain())
            is Resource.Error -> Resource.Error(resource.message, resource.cause)
            Resource.Loading -> Resource.Loading
        }
    }
}
