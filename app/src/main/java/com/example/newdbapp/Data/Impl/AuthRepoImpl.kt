package com.example.newdbapp.Data.Impl

import com.example.newdbapp.Data.Dto.AppStatusResponseDto
import com.example.newdbapp.Data.Dto.LoginResponseDto
import com.example.newdbapp.Data.Dto.ResultDto
import com.example.newdbapp.Data.network.ApiService
import com.example.newdbapp.Domain.Repository.AuthRepository
import com.example.newdbapp.Domain.Repository.BaseRepository
import com.example.newdbapp.Utility.Resource

class AuthRepoImpl(val apiService: ApiService) : AuthRepository, BaseRepository() {
    override suspend fun isRegister(params: MutableMap<String, Any>): Resource<ResultDto> {
          return safeApiCall { apiService.checkRegister(params["deviceId"] as String) }

    }

    override suspend fun login(params: MutableMap<String, Any>): Resource<LoginResponseDto?> {
        return safeApiCall { apiService.deliveryBoyLogin(params["username"] as String, password = params["password"] as String) }
    }

    override suspend fun checkAppStatus(params: MutableMap<String, Any>): Resource<AppStatusResponseDto?> {
        return safeApiCall { apiService.getCheckInStatus(dboy_id = params["dboy_id"] as Int) }
    }

}