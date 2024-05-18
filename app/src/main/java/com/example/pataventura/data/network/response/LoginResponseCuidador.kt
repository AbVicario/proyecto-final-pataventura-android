package com.example.pataventura.data.network.response

import com.google.gson.annotations.SerializedName

data class LoginResponseCuidador (
    @SerializedName("data") val data: DataTokenCuidador,
    @SerializedName("ok") val ok: Boolean,
    @SerializedName("status") val status: Int,
)
data class DataTokenCuidador (
    @SerializedName("token") val token: String
)