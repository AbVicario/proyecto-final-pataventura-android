package com.example.pataventura.data.network.response

import com.google.gson.annotations.SerializedName

data class MascotasResponse(
    @SerializedName("data") val data: List<DataMascota>,
    @SerializedName("status") val status: Int,
    @SerializedName("ok") val ok: Boolean
)

data class MascotaResponse(
    @SerializedName("data") val data: DataMascota,
    @SerializedName("status") val status: Int,
    @SerializedName("ok") val ok: Boolean
)

data class DataMascota(
    @SerializedName("id_mascota") var idMascota: Int,
    @SerializedName("nombre") var nombre: String,
    @SerializedName("num_chip") var numChip: String,
    @SerializedName("edad") var edad: String = "",
    @SerializedName("imagen") var imagen: ByteArray? = null,
    @SerializedName("tamanyo") var tamanyo: String = "",
    @SerializedName("peso") var peso: Double = 0.0,
    @SerializedName("tipo") var tipo: String,
    @SerializedName("raza") var raza: String = "",
    @SerializedName("observacion") var observacion: String = "",
    @SerializedName("color") var color: String,
    @SerializedName("sexo") var sexo: String
)