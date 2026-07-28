package com.example.newdbapp.Domain.Model

import kotlinx.serialization.Serializable

@Serializable
data class CustomerListModel(
    val status: String? = null,
    val msg: String? = null,
    val customerListData: CustomerListData? = null
)

@Serializable
data class CustomerListData(
    val isCheckoutEnable: String? = null,
    val status: String? = null,
    val msg: String? = null,
    val isProductListDisplay: Int? = null,
    val isNotOpenNotTakenImageMandatory: String? = null,
    val isImageClickMandatoryForDb: String? = null,
    val openBidCount: Int? = null,
    val custListCount: String? = null,
    val totalPendingAmount: Int? = null,
    val customerList: List<CustomerItem>? = null,
    val newCustomerPopupImage: String? = null,
    val dbTripStarted: String? = null
)

@Serializable
data class CustomerItem(
    val userId: String? = null,
    val isNewCustomer: String? = null,
    val customerDeliveryCount: Int? = null,
    val productListUrl: String? = null,
    val postpaidCreditLimitStatus: Int? = null,
    val splMsgCount: Int? = null,
    val splMsgList: List<String>? = null,
    val dDate: String? = null,
    val mobile: String? = null,
    val mobile2: String? = null,
    val customerId: String? = null,
    val bell: Int? = null,
    val niceToHaveReq: String? = null,
    val bgColor: String? = null,
    val userDeliverySlot: String? = null,
    val callBeforeDelivery: Int? = null,
    val ringTheBell: Int? = null,
    val deliveryInstruction: String? = null,
    val bagHangingToDoor: Int? = null,
    val doorImageUrl: String? = null,
    val voiceNote: String? = null,
    val username: String? = null,
    val specialCxStatusUrl: String? = null,
    val address: String? = null,
    val latitude: String? = null,
    val longitude: String? = null,
    val isLocationSet: Int? = null,
    val paymentOption: String? = null,
    val pendingAmount: Int? = null,
    val totalOutstandingTillToday: Long? = null,
    val todayCollection: Int? = null,
    val isPaymentButtonEnable: Int? = null,
    val paymentType: String? = null,
    val isUnsubscribeButtonEnable: Int? = null,
    val custDeliveryList: CustDeliveryList? = null,
    val emptyBottlesCollectionDetails: EmptyBottlesDetails? = null,
    val listType: String? = null
)

@Serializable
data class CustDeliveryList(
    val imageFormat: String? = null,
    val imageContent: String? = null,
    val pod: Pod? = null,
    val isEditable: String? = null,
    val backgroundColor: String? = null,
    val isDbCollectEmptyBottle: Int? = null,
    val pendingEmptyBottle: Int? = null,
    val collectedEmptyBottles: Int? = null,
    val collectedBrokenBottles: Int? = null,
    val toBeCollectedEmptyBottle: Int? = null,
    val emptyBottlesCollectionDetails: EmptyBottlesDetails? = null,
    val status: String? = null,
    val msg: String? = null,
    val multiDeliveryList: List<MultiDeliveryItem>? = null,
    val missedDeliveryReason: List<String>? = null,
    val partialDeliveryReason: List<String>? = null,
    val emptyBottleCollectionReason: List<String>? = null
)

@Serializable
data class Pod(
    val image: List<String>? = null,
    val video: List<String>? = null
)

@Serializable
data class EmptyBottlesDetails(
    val emptyBottlesImage: List<String>? = null,
    val emptyBottlesVideo: List<String>? = null,
    val reason: String? = null
)

@Serializable
data class MultiDeliveryItem(
    val deliveryId: Long? = null,
    val dDate: String? = null,
    val deliveryStatus: Int? = null,
    val orderQty: Int? = null,
    val billId: Long? = null,
    val maxDeliveryQuantity: Int? = null,
    val productName: String? = null,
    val productColor: String? = null,
    val deliveredQty: Int? = null
)
