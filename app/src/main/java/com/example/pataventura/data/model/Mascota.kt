package com.example.pataventura.data.model

import com.google.gson.annotations.SerializedName

data class Mascota(
    @SerializedName("nombre") val nombre: String,
    @SerializedName("num_chip") val num_chip: String,
    @SerializedName("imagen") val imagen: String,
    @SerializedName("tipo") val tipo: String,
    @SerializedName("raza") val raza: String,
    @SerializedName("observacion") val observacion: String,
    @SerializedName("edad") val edad: Int,
    @SerializedName("tamanyo") val tamanyo: Float

)
