package com.example.newdbapp.Utility

enum class ListType(val type: String) {
    DBVIEW_TODAY("dbview_today"),
    TODAY_CUST_LIST_PENDING("today_cust_list_pending"),
    TODAY_CUST_LIST_DELIVERD("today_cust_list_delivered"),
    TODAY_CUST_LIST_NOTTAKEN("today_cust_list_nottaken"),
    TODAY_CUST_LIST_PAUSED("today_cust_list_paused"),
    TODAY_CUST_LIST_UNSETTLED("today_cust_list_unsettled"),
    TODAY_CUST_LIST_REQUEST("db_collection_request_cust_list");

    companion object {
        fun fromType(type: String): ListType? {
            return entries.find { it.type == type }
        }
    }
}
