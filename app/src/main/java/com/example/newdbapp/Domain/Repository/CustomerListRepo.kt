package com.example.newdbapp.Domain.Repository

import com.example.newdbapp.Data.Dto.CustomerListResponseDto
import com.example.newdbapp.Utility.Resource

interface CustomerListRepo {
    suspend fun fetchCustomerList(params: MutableMap<String,String>): Resource<CustomerListResponseDto?>
}