package com.example.pataventura.data.network.response

import com.google.gson.annotations.SerializedName

data class LoginResponseTutor (
    @SerializedName("data") val data: DataTokenTutor,
    @SerializedName("ok") val ok: Boolean,
    @SerializedName("status") val status: Int,
)
data class DataTokenTutor (
    @SerializedName("token") val token: String
)