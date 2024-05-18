package com.example.pataventura.data.model

import com.google.gson.annotations.SerializedName

data class MascotaModel(
    @SerializedName("id_mascota") var idMascota: Int,
    @SerializedName("nombre") var nombre: String,
    @SerializedName("num_chip") var numChip: String,
    @SerializedName("edad") var edad: String = "",
    @SerializedName("imagen") var imagen: String = "",
    @SerializedName("tamanyo") var tamanyo: Double,
    @SerializedName("peso") var peso: Double,
    @SerializedName("tipo") var tipo: String,
    @SerializedName("raza") var raza: String = "",
    @SerializedName("observacion") var observacion: String = "",
    @SerializedName("color") var color: String
)
