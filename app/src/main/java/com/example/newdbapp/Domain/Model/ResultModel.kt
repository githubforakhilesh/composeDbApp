package com.example.newdbapp.Domain.Model

data class ResultModel(
    val status: String,
    val message: String,
    val guestUserId: Int,
    val deviceId: String,
    val userId: Int,
    val termsUrl: String,
    val isPrimeFlow: Boolean,
    val baseScreenHtml: String,
    val buttonCaption: String,
    val customization: CustomizationDomain
)

data class CustomizationDomain(
    val displayDeliveryCalendar: Boolean,
    val displayReferralBanner: Boolean,
    val userQualifiesMilkHeroOffer: Boolean,
    val isAccountBlocked: Boolean,
    val isAutoFillMobileNumber: Boolean,
    val profileStatus: String,
    val isWhatsappOtpEnable: Boolean,
    val isLogoutEnable: Boolean
)

