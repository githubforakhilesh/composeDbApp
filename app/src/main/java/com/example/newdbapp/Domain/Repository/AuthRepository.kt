package com.example.newdbapp.Domain.Repository

import com.example.newdbapp.Data.Dto.AppStatusResponseDto
import com.example.newdbapp.Data.Dto.LoginResponseDto
import com.example.newdbapp.Data.Dto.ResultDto
import com.example.newdbapp.Domain.Model.ResultModel
import com.example.newdbapp.Utility.Resource

interface AuthRepository {
    suspend fun isRegister(params: MutableMap<String,Any>) : Resource<ResultDto?>
    suspend fun login(params: MutableMap<String,Any>) : Resource<LoginResponseDto?>
    suspend fun checkAppStatus(params: MutableMap<String,Any>) : Resource<AppStatusResponseDto?>
    suspend fun registerDevice(params: MutableMap<String,String>) : Resource<ResultDto?>

}