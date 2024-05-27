package com.example.pataventura.data.network.response

import com.google.gson.annotations.SerializedName

data class ValoracionResponse(
    @SerializedName("data") val data: List<DataValoracion>,
    @SerializedName("status") val status: Int,
    @SerializedName("ok") val ok: Boolean
)

data class DataValoracion(
    @SerializedName("id_valoracion") val idValoracion: Int,
    @SerializedName("puntuacion") val puntuacion: Double = 1.0,
    @SerializedName("descripcion") val descripcion: String = "",
    @SerializedName("alias_tutor") val aliasTutor: String = "",
    @SerializedName("imagen_tutor") val imagenTutor: ByteArray
)