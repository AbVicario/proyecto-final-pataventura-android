package com.example.pataventura.data.network.response
import com.google.gson.annotations.SerializedName
data class CustomResponse(
    @SerializedName("data") val data: String,
    @SerializedName("status") val status: Int,
    @SerializedName("ok") val ok: Boolean
)
