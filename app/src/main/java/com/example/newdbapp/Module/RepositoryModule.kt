package com.example.newdbapp.Module

import com.example.newdbapp.Data.Impl.AuthRepoImpl
import com.example.newdbapp.Data.Impl.CustomerListRepoImpl
import com.example.newdbapp.Data.network.ApiService
import com.example.newdbapp.Domain.Repository.AuthRepository
import com.example.newdbapp.Domain.Repository.CustomerListRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(apiService: ApiService): AuthRepository {
        return AuthRepoImpl(apiService)
    }
    @Provides
    @Singleton
    fun provideCustomerListRepository(apiService: ApiService): CustomerListRepo {
        return CustomerListRepoImpl(apiService)
    }
}