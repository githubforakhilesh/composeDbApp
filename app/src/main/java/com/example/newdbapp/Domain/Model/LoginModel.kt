package com.example.newdbapp.Domain.Model

import com.example.newdbapp.Data.Dto.UserDto
import kotlinx.serialization.SerialName

data class LoginModel(

    private var mMsg: String? = null,

    private var mStatus: String? = null,

    private var mIsCheckinCheckoutImageMandatory: String? = null,


    private var mUser: UserModel? = null,


    private var accessToken: String? = null,


    private var workingStatus: String? = null,


    private var dbRoles: MutableList<String?>? = null,


    private var dispatcherId: String? = null
)
data class UserModel(
    private var mId: Int = 0,


    private var mUsername: String? = null,


    private var isProfile: Int? = 0,


    private var mroleId: Int? = 0,


    private var mMsg: String? = null,

    private var status: String? = null
)

