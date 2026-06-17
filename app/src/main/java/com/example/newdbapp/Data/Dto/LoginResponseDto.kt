package com.example.newdbapp.Data.Dto

import com.example.newdbapp.Domain.Model.LoginModel
import com.example.newdbapp.Domain.Model.UserModel
import kotlinx.serialization.SerialName

data class LoginResponseDto (
    @SerialName("msg")
    private var mMsg: String? = null,
    @SerialName("status")
    private var mStatus: String? = null,

    @SerialName("is_checkin_checkout_image_mandatory")
    private var mIsCheckinCheckoutImageMandatory: String? = null,

    @SerialName("user")
    private var mUser: UserDto? = null,

    @SerialName("access_token")
    private var accessToken: String? = null,

    @SerialName("working_status")
    private var workingStatus: String? = null,

    @SerialName("db_roles")
    private var dbRoles: MutableList<String?>? = null,

    @SerialName("dispatcher_id")
    private var dispatcherId: String? = null

) {
    fun toModel(): LoginModel{
        return LoginModel(
            mMsg = this.mMsg,
            mStatus = this.mStatus,
            mIsCheckinCheckoutImageMandatory = this.mIsCheckinCheckoutImageMandatory,
            mUser = this.mUser?.toModel(),
            accessToken = this.accessToken,
            workingStatus = this.workingStatus,
            dbRoles = this.dbRoles,
            dispatcherId = this.dispatcherId

        )
    }
}

data class UserDto(
    @SerialName("id")
    private var mId: Int = 0,

    @SerialName("username")
    private var mUsername: String? = null,

    @SerialName("is_profile")
    private var isProfile: Int? = 0,

    @SerialName("role_id")
    private var mroleId: Int? = 0,

    @SerialName("msg")
    private var mMsg: String? = null,

    @SerialName("status")
    private var status: String? = null
){
    fun toModel(): UserModel{
        return UserModel(
            mId = this.mId,
            mUsername = this.mUsername,
            isProfile = this.isProfile,
            mroleId = this.mroleId,
            mMsg = this.mMsg,
            status = this.status

        )
    }
}