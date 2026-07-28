package com.example.newdbapp.Data.Impl

import com.example.newdbapp.Data.Dto.AppStatusResponseDto
import com.example.newdbapp.Data.Dto.LoginResponseDto
import com.example.newdbapp.Data.Dto.ResultDto
import com.example.newdbapp.Data.network.ApiService
import com.example.newdbapp.Domain.Repository.AuthRepository
import com.example.newdbapp.Domain.Repository.BaseRepository
import com.example.newdbapp.Utility.ParamsConstant
import com.example.newdbapp.Utility.Resource

class AuthRepoImpl(val apiService: ApiService) : AuthRepository, BaseRepository() {
    override suspend fun isRegister(params: MutableMap<String, Any>): Resource<ResultDto?> {
        val deviceId = params[ParamsConstant.DEVICE_ID]?.toString() ?: ""
        return safeApiCall { apiService.checkRegister(deviceId) }
    }

    override suspend fun login(params: MutableMap<String, Any>): Resource<LoginResponseDto?> {
        val username = params[ParamsConstant.USERNAME]?.toString() ?: ""
        val password = params[ParamsConstant.PASSWORD]?.toString() ?: ""
        return safeApiCall { apiService.deliveryBoyLogin(username, password) }
    }

    override suspend fun checkAppStatus(params: MutableMap<String, Any>): Resource<AppStatusResponseDto?> {
        val dboyIdString = params[ParamsConstant.DBOY_ID]?.toString() ?: "0"
        val dboyId = dboyIdString.toIntOrNull() ?: 0
        return safeApiCall { apiService.getCheckInStatus(dboy_id = dboyId) }
    }

    override suspend fun registerDevice(params: MutableMap<String, String>): Resource<ResultDto?> {
        return safeApiCall { apiService.registerDevice(params) }
    }
}