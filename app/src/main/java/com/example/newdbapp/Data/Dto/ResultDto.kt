package com.example.newdbapp.Data.Dto

import com.example.newdbapp.Domain.Model.ResultModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer


data class ResultDto(
    @SerialName("status")
    private var status: String? = null,

    @SerialName("msg")
    private var meesage: String? = null,

    @SerialName("user_id")
    private var userId: String? = null,

    @SerialName("is_profile")
    private var profileStatus: Int = 0,

    @SerialName("role_id")
    private var roleId: Int = 0,

    @SerialName("user_mobile")
    private var mobile_no: String? = null,

    @SerialName("unsettled")
    private var customer_status: String? = null,

    @SerialName("subscription_id")
    private var subsId: Int = 0
) {
    fun toDomain(): ResultModel {
        return ResultModel(
            status = status,
            meesage = meesage,
            userId = userId,
            profileStatus = profileStatus,
            roleId = roleId,
            mobile_no = mobile_no,
            customer_status = customer_status,
            subsId = subsId
        )
    }
}
