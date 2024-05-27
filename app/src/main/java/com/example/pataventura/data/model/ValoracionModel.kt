package com.example.pataventura.data.model

import com.google.gson.annotations.SerializedName

data class ValoracionModel(
    @SerializedName("id_valoracion") val idValoracion: Int,
    @SerializedName("puntuacion") val puntuacion: Double = 1.0,
    @SerializedName("descripcion") val descripcion: String = "",
    @SerializedName("alias_tutor") val aliasTutor: String = "",
    @SerializedName("imagen_tutor") val imagenTutor: ByteArray
)
