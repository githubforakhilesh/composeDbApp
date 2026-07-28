package com.example.newdbapp.Data.Dto

import com.example.newdbapp.Domain.Model.CustomizationDomain
import com.example.newdbapp.Domain.Model.ResultModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class ResultDto(
    @SerialName("status") val status: String? = null,
@SerialName("msg") val msg: String? = null,
@SerialName("guest_user_id") val guestUserId: Int? = null,
@SerialName("device_id") val deviceId: String? = null,
@SerialName("user_id") val userId: Int? = null,
@SerialName("terms_url") val termsUrl: String? = null,
@SerialName("customization") val customization: CustomizationDto? = null,
@SerialName("free_trial_prime_info") val freeTrialPrimeInfo: FreeTrialPrimeInfoDto? = null,
@SerialName("referral_policy_version") val referralPolicyVersion: Int? = null,
@SerialName("access_token") val accessToken: String? = null,
@SerialName("refresh_token") val refreshToken: String? = null
){
    fun toDomain(): ResultModel
    {
        return ResultModel(
            status = this.status ?: "Unknown",
            message = this.msg ?: "No message provided",
            guestUserId = this.guestUserId ?: 0,
            deviceId = this.deviceId ?: "0",
            userId = this.userId ?: 0,
            termsUrl = this.termsUrl ?: "",
            isPrimeFlow = this.freeTrialPrimeInfo?.isPrimeFlow == 1,
            baseScreenHtml = this.freeTrialPrimeInfo?.baseScreenHtml ?: "",
            buttonCaption = this.freeTrialPrimeInfo?.buttonCaption ?: "",
            customization = CustomizationDomain(
                displayDeliveryCalendar = this.customization?.displayDeliveryCalender == 1,
                displayReferralBanner = this.customization?.displayReferalBanner == 1,
                userQualifiesMilkHeroOffer = this.customization?.userQualifiesMilkHeroOffer == 1,
                isAccountBlocked = this.customization?.isAccountBlocked == 1,
                isAutoFillMobileNumber = this.customization?.isAutoFillMobileNumber == 1,
                profileStatus = this.customization?.profileStatus ?: "",
                isWhatsappOtpEnable = this.customization?.isWhatsappOtpEnable == 1,
                isLogoutEnable = this.customization?.isLogoutEnable == 1
            )
        )
    }
}

@Serializable
data class CustomizationDto(
    @SerialName("display_delivery_calender") val displayDeliveryCalender: Int? = null,
    @SerialName("display_referal_banner") val displayReferalBanner: Int? = null,
    @SerialName("user_qualifies_milk_hero_offer") val userQualifiesMilkHeroOffer: Int? = null,
    @SerialName("is_account_blocked") val isAccountBlocked: Int? = null,
    @SerialName("is_auto_fill_mobile_number") val isAutoFillMobileNumber: Int? = null,
    @SerialName("user_address_detail_event") val userAddressDetailEvent: String? = null,
    @SerialName("profile_status") val profileStatus: String? = null,
    @SerialName("user_question_answer_status") val userQuestionAnswerStatus: Int? = null,
    @SerialName("is_whatsapp_otp_enable") val isWhatsappOtpEnable: Int? = null,
    @SerialName("is_logout_enable") val isLogoutEnable: Int? = null,
    @SerialName("is_log_enabled") val isLogEnabled: Int? = null,
    @SerialName("track_app_event") val trackAppEvent: Int? = null
)

@Serializable
data class FreeTrialPrimeInfoDto(
    @SerialName("is_prime_flow") val isPrimeFlow: Int? = null,
    @SerialName("base_screen_html") val baseScreenHtml: String? = null,
    @SerialName("button_caption") val buttonCaption: String? = null
)
