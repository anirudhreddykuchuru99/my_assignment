package com.action.myassignment.data.entity

import com.google.gson.annotations.SerializedName

data class Data(
    val avatar: String,
    val email: String,
    @SerializedName("first_name") val first_name: String,
    val id: Int,
    @SerializedName("last_name") val last_name: String
)