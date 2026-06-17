package com.example.newdbapp.Domain.Model

data class ResultModel(
    private var status: String? = null,

    private var meesage: String? = null,

    private var userId: String? = null,


    private var profileStatus: Int = 0,

    private var roleId: Int = 0,

    private var mobile_no: String? = null,
    private var customer_status: String? = null,
    private var subsId: Int = 0
)
