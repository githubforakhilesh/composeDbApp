package com.example.newdbapp.Data.network

import com.example.newdbapp.Utility.PreferenceManager
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val preferenceManager: PreferenceManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        // 1. Get the token from DataStore synchronously
        // We use runBlocking because Interceptors run on background threads already
        val token = runBlocking {
            preferenceManager.accessToken.firstOrNull()
        }

        // 2. Get the original request
        val originalRequest = chain.request()

        // 3. Create a new request and add the header if the token exists
        val requestBuilder = originalRequest.newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")

        if (!token.isNullOrEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        // 4. Proceed with the new request
        return chain.proceed(requestBuilder.build())
    }
}