package com.example.pataventura.data.model

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class TutorModel(
    @SerializedName("id_usuario") var idUsuario: Int,
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String,
    @SerializedName("telefono") var telefono: String,
    @SerializedName("nombre") var nombre: String,
    @SerializedName("apellido") var apellido: String,
    @SerializedName("imagen") var imagen: ByteArray,
    @SerializedName("alias") var alias: String,
    @SerializedName("direccion") var direccion: String
)


