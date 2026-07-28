package com.example.newdbapp.Data.Impl

import com.example.newdbapp.Data.Dto.CustomerListResponseDto
import com.example.newdbapp.Data.network.ApiService
import com.example.newdbapp.Domain.Repository.BaseRepository
import com.example.newdbapp.Domain.Repository.CustomerListRepo
import com.example.newdbapp.Utility.Resource

class CustomerListRepoImpl (val service: ApiService): CustomerListRepo, BaseRepository() {
    override suspend fun fetchCustomerList(params: MutableMap<String, String>): Resource<CustomerListResponseDto?> {
        return safeApiCall { service.fetchCustomerList(params) }
    }
}