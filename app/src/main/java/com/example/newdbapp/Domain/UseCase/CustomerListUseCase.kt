package com.example.newdbapp.Domain.UseCase

import com.example.newdbapp.Domain.Repository.CustomerListRepo
import com.example.newdbapp.Utility.Resource
import javax.inject.Inject

class CustomerListUseCase @Inject constructor(private val customerListRepo: CustomerListRepo) {
    suspend operator fun invoke(params: MutableMap<String, String>) = when(val result = customerListRepo.fetchCustomerList(params)) {
        is Resource.Error -> {
            Resource.Error(result.message)
        }
        Resource.Loading -> {
            Resource.Loading
        }
        is Resource.Success -> {
            Resource.Success(result.data)
        }
    }
}