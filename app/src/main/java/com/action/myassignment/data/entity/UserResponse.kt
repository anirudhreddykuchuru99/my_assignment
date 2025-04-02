package com.action.myassignment.data.entity

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data") val `data`: List<Data>,
    val page: Int,
    @SerializedName("per_page") val per_page: Int,
    val support: Support,
    val total: Int,
    @SerializedName("total_pages") val total_pages: Int
)