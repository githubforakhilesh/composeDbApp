package com.example.newdbapp.Data.network

import com.example.newdbapp.Data.Dto.AppStatusResponseDto
import com.example.newdbapp.Data.Dto.LoginResponseDto
import com.example.newdbapp.Data.Dto.ResultDto
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("db-users/isregister")
    suspend fun checkRegister(@Field("deviceId") deviceId:String) : ResultDto

    @FormUrlEncoded
    @POST("db-users/dboy-app-state")
    suspend fun getCheckInStatus(@Field("dboy_id") dboy_id: Int): AppStatusResponseDto?

    @FormUrlEncoded
    @POST("db-users/login")
    fun deliveryBoyLogin(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): LoginResponseDto?
}


