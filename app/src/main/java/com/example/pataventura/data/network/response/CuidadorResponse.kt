package com.example.pataventura.data.network.response

import com.google.gson.annotations.SerializedName
data class CuidadorResponse(
    @SerializedName("data") val data: DataCuidador,
    @SerializedName("status") val status: Int,
    @SerializedName("ok") val ok: Boolean
)
data class DataCuidador(
    @SerializedName("id_usuario") var idUsuario: Int,
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String,
    @SerializedName("telefono") var telefono: String,
    @SerializedName("nombre") var nombre: String,
    @SerializedName("apellido") var apellido: String,
    @SerializedName("imagen") var imagen: ByteArray,
    @SerializedName("alias") var alias: String,
    @SerializedName("direccion") var direccion: String,
)
