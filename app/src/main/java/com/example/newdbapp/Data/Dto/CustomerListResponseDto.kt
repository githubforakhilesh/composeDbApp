package com.example.newdbapp.Data.Dto

import com.example.newdbapp.Domain.Model.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomerListResponseDto(
    @SerialName("status") val status: String? = null,
    @SerialName("msg") val msg: String? = null,
    @SerialName("responseGetDbCustomerList") val data: DbCustomerListContainerDto? = null
) {
    fun toDomain(): CustomerListModel {
        return CustomerListModel(
            status = status,
            msg = msg,
            customerListData = data?.toDomain()
        )
    }
}

@Serializable
data class DbCustomerListContainerDto(
    @SerialName("is_checkout_enable") val isCheckoutEnable: String? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("msg") val msg: String? = null,
    @SerialName("is_product_list_display") val isProductListDisplay: Int? = null,
    @SerialName("is_not_open_not_taken_image_mandatory") val isNotOpenNotTakenImageMandatory: String? = null,
    @SerialName("is_image_click_mandatory_for_db") val isImageClickMandatoryForDb: String? = null,
    @SerialName("open_bid_count") val openBidCount: Int? = null,
    @SerialName("cust_list_count") val custListCount: String? = null,
    @SerialName("total_pending_amount_of_all_customers") val totalPendingAmount: Int? = null,
    @SerialName("customer_list") val customerList: List<CustomerDto>? = null,
    @SerialName("new_customer_popup_image") val newCustomerPopupImage: String? = null,
    @SerialName("db_trip_started") val dbTripStarted: String? = null
) {
    fun toDomain(): CustomerListData {
        return CustomerListData(
            isCheckoutEnable = isCheckoutEnable,
            status = status,
            msg = msg,
            isProductListDisplay = isProductListDisplay,
            isNotOpenNotTakenImageMandatory = isNotOpenNotTakenImageMandatory,
            isImageClickMandatoryForDb = isImageClickMandatoryForDb,
            openBidCount = openBidCount,
            custListCount = custListCount,
            totalPendingAmount = totalPendingAmount,
            customerList = customerList?.map { it.toDomain() },
            newCustomerPopupImage = newCustomerPopupImage,
            dbTripStarted = dbTripStarted
        )
    }
}

@Serializable
data class CustomerDto(
    @SerialName("user_id") val userId: String? = null,
    @SerialName("isNewCustomer") val isNewCustomer: String? = null,
    @SerialName("customerDeliveryCount") val customerDeliveryCount: Int? = null,
    @SerialName("product_list_url") val productListUrl: String? = null,
    @SerialName("postpaid_credit_limit_status") val postpaidCreditLimitStatus: Int? = null,
    @SerialName("spl_msg_count") val splMsgCount: Int? = null,
    @SerialName("spl_msg_list") val splMsgList: List<String>? = null, // assuming list of strings if populated
    @SerialName("d_date") val dDate: String? = null,
    @SerialName("mobile") val mobile: String? = null,
    @SerialName("mobile2") val mobile2: String? = null,
    @SerialName("customer_id") val customerId: String? = null,
    @SerialName("bell") val bell: Int? = null,
    @SerialName("nice_to_have_req") val niceToHaveReq: String? = null,
    @SerialName("bg_color") val bgColor: String? = null,
    @SerialName("user_delivery_slot") val userDeliverySlot: String? = null,
    @SerialName("call_before_delivery") val callBeforeDelivery: Int? = null,
    @SerialName("ring_the_bell") val ringTheBell: Int? = null,
    @SerialName("delivery_instruction") val deliveryInstruction: String? = null,
    @SerialName("bag_hanging_to_door") val bagHangingToDoor: Int? = null,
    @SerialName("door_image_url") val doorImageUrl: String? = null,
    @SerialName("voice_note") val voiceNote: String? = null,
    @SerialName("username") val username: String? = null,
    @SerialName("special_cx_status_url") val specialCxStatusUrl: String? = null,
    @SerialName("address") val address: String? = null,
    @SerialName("latitude") val latitude: String? = null,
    @SerialName("longitude") val longitude: String? = null,
    @SerialName("is_location_set") val isLocationSet: Int? = null,
    @SerialName("payment_option") val paymentOption: String? = null,
    @SerialName("pending_amount") val pendingAmount: Int? = null,
    @SerialName("total_outstanding_till_today") val totalOutstandingTillToday: Long? = null,
    @SerialName("today_collection") val todayCollection: Int? = null,
    @SerialName("is_payment_button_enable") val isPaymentButtonEnable: Int? = null,
    @SerialName("payment_type") val paymentType: String? = null,
    @SerialName("is_unsubscribe_button_enable") val isUnsubscribeButtonEnable: Int? = null,
    @SerialName("cust_delivery_list") val custDeliveryList: CustDeliveryListDto? = null,
    @SerialName("empty_bottles_collection_details") val emptyBottlesCollectionDetails: EmptyBottlesDetailsDto? = null,
    @SerialName("list_type") val listType: String? = null
) {
    fun toDomain(): CustomerItem {
        return CustomerItem(
            userId = userId,
            isNewCustomer = isNewCustomer,
            customerDeliveryCount = customerDeliveryCount,
            productListUrl = productListUrl,
            postpaidCreditLimitStatus = postpaidCreditLimitStatus,
            splMsgCount = splMsgCount,
            splMsgList = splMsgList,
            dDate = dDate,
            mobile = mobile,
            mobile2 = mobile2,
            customerId = customerId,
            bell = bell,
            niceToHaveReq = niceToHaveReq,
            bgColor = bgColor,
            userDeliverySlot = userDeliverySlot,
            callBeforeDelivery = callBeforeDelivery,
            ringTheBell = ringTheBell,
            deliveryInstruction = deliveryInstruction,
            bagHangingToDoor = bagHangingToDoor,
            doorImageUrl = doorImageUrl,
            voiceNote = voiceNote,
            username = username,
            specialCxStatusUrl = specialCxStatusUrl,
            address = address,
            latitude = latitude,
            longitude = longitude,
            isLocationSet = isLocationSet,
            paymentOption = paymentOption,
            pendingAmount = pendingAmount,
            totalOutstandingTillToday = totalOutstandingTillToday,
            todayCollection = todayCollection,
            isPaymentButtonEnable = isPaymentButtonEnable,
            paymentType = paymentType,
            isUnsubscribeButtonEnable = isUnsubscribeButtonEnable,
            custDeliveryList = custDeliveryList?.toDomain(),
            emptyBottlesCollectionDetails = emptyBottlesCollectionDetails?.toDomain(),
            listType = listType
        )
    }
}

@Serializable
data class CustDeliveryListDto(
    @SerialName("image_format") val imageFormat: String? = null,
    @SerialName("image_content") val imageContent: String? = null,
    @SerialName("pod") val pod: PodDto? = null,
    @SerialName("is_editable") val isEditable: String? = null,
    @SerialName("background_color") val backgroundColor: String? = null,
    @SerialName("is_db_collect_empty_bottle") val isDbCollectEmptyBottle: Int? = null,
    @SerialName("pending_empty_bottle") val pendingEmptyBottle: Int? = null,
    @SerialName("collected_empty_bottles") val collectedEmptyBottles: Int? = null,
    @SerialName("collected_broken_bottles") val collectedBrokenBottles: Int? = null,
    @SerialName("to_be_collected_empty_bottle") val toBeCollectedEmptyBottle: Int? = null,
    @SerialName("empty_bottles_collection_details") val emptyBottlesCollectionDetails: EmptyBottlesDetailsDto? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("msg") val msg: String? = null,
    @SerialName("multi_delivery_list") val multiDeliveryList: List<MultiDeliveryItemDto>? = null,
    @SerialName("missed_delivery_reason") val missedDeliveryReason: List<String>? = null,
    @SerialName("partial_delivery_reason") val partialDeliveryReason: List<String>? = null,
    @SerialName("empty_bottle_collection_reason") val emptyBottleCollectionReason: List<String>? = null
) {
    fun toDomain(): CustDeliveryList {
        return CustDeliveryList(
            imageFormat = imageFormat,
            imageContent = imageContent,
            pod = pod?.toDomain(),
            isEditable = isEditable,
            backgroundColor = backgroundColor,
            isDbCollectEmptyBottle = isDbCollectEmptyBottle,
            pendingEmptyBottle = pendingEmptyBottle,
            collectedEmptyBottles = collectedEmptyBottles,
            collectedBrokenBottles = collectedBrokenBottles,
            toBeCollectedEmptyBottle = toBeCollectedEmptyBottle,
            emptyBottlesCollectionDetails = emptyBottlesCollectionDetails?.toDomain(),
            status = status,
            msg = msg,
            multiDeliveryList = multiDeliveryList?.map { it.toDomain() },
            missedDeliveryReason = missedDeliveryReason,
            partialDeliveryReason = partialDeliveryReason,
            emptyBottleCollectionReason = emptyBottleCollectionReason
        )
    }
}

@Serializable
data class PodDto(
    @SerialName("image") val image: List<String>? = null,
    @SerialName("video") val video: List<String>? = null
) {
    fun toDomain(): Pod {
        return Pod(
            image = image,
            video = video
        )
    }
}

@Serializable
data class EmptyBottlesDetailsDto(
    @SerialName("empty_bottles_image") val emptyBottlesImage: List<String>? = null,
    @SerialName("empty_bottles_video") val emptyBottlesVideo: List<String>? = null,
    @SerialName("reason") val reason: String? = null
) {
    fun toDomain(): EmptyBottlesDetails {
        return EmptyBottlesDetails(
            emptyBottlesImage = emptyBottlesImage,
            emptyBottlesVideo = emptyBottlesVideo,
            reason = reason
        )
    }
}

@Serializable
data class MultiDeliveryItemDto(
    @SerialName("delivery_id") val deliveryId: Long? = null,
    @SerialName("d_date") val dDate: String? = null,
    @SerialName("delivery_status") val deliveryStatus: Int? = null,
    @SerialName("order_qty") val orderQty: Int? = null,
    @SerialName("bill_id") val billId: Long? = null,
    @SerialName("max_delivery_quantity") val maxDeliveryQuantity: Int? = null,
    @SerialName("product_name") val productName: String? = null,
    @SerialName("product_color") val productColor: String? = null,
    @SerialName("delivered_qty") val deliveredQty: Int? = null
) {
    fun toDomain(): MultiDeliveryItem {
        return MultiDeliveryItem(
            deliveryId = deliveryId,
            dDate = dDate,
            deliveryStatus = deliveryStatus,
            orderQty = orderQty,
            billId = billId,
            maxDeliveryQuantity = maxDeliveryQuantity,
            productName = productName,
            productColor = productColor,
            deliveredQty = deliveredQty
        )
    }
}