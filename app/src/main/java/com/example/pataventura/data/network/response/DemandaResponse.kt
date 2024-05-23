package com.example.pataventura.data.network.response

import com.google.gson.annotations.SerializedName
import com.google.type.DateTime

data class DemandaResponse(
    @SerializedName("data") val data: DataDemanda,
    @SerializedName("status") val status: Int,
    @SerializedName("ok") val ok: Boolean
)
data class DataDemanda(
    @SerializedName("fechaInicio") var fechaInicio: DateTime,
    @SerializedName("fechaFin") var fechaFin: DateTime,
    @SerializedName("precio") var precio: Double,
    @SerializedName("descripcion") var descripcion: String,
    @SerializedName("estado") var estado: String,
)