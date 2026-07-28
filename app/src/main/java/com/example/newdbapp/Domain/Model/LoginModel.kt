package com.example.newdbapp.Domain.Model

import com.example.newdbapp.Data.Dto.UserDto
import kotlinx.serialization.SerialName

data class LoginModel(

     var mMsg: String? = null,

     var mStatus: String? = null,

     var mIsCheckinCheckoutImageMandatory: String? = null,


    var mUser: UserModel? = null,


     var accessToken: String? = null,


     var workingStatus: String? = null,


     var dbRoles: MutableList<String?>? = null,


     var dispatcherId: String? = null
)
data class UserModel(
     var mId: Int = 0,


     var mUsername: String? = null,


     var isProfile: Int? = 0,


     var mroleId: Int? = 0,


     var mMsg: String? = null,

     var status: String? = null
)

