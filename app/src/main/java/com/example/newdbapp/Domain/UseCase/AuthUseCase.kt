package com.example.newdbapp.Domain.UseCase

import com.example.newdbapp.Data.Dto.ResultDto
import com.example.newdbapp.Domain.Repository.AuthRepository
import com.example.newdbapp.Utility.Resource
import javax.inject.Inject

class AuthUseCase @Inject constructor(private val authRepository: AuthRepository) {
     suspend operator fun invoke(params: MutableMap<String,Any>) : Resource<ResultDto> {
         return authRepository.isRegister(params)
     }

}