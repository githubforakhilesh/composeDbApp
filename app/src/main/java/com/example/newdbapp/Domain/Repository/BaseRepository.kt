package com.example.newdbapp.Domain.Repository

import com.example.newdbapp.Utility.Resource

abstract class BaseRepository  {
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        return try {
            Resource.Success(apiCall.invoke())
        } catch (e: Exception) {
            Resource.Error(message = e.localizedMessage ?: "Unknown Error", cause = e)
        }
    }
}