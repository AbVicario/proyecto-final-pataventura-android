package com.example.pataventura.data.network.response

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("data") val data: Data,
)
data class Data (
    @SerializedName("token") val token: String,
    @SerializedName("ok") val ok: Boolean,
    @SerializedName("status") val status: Int,
)