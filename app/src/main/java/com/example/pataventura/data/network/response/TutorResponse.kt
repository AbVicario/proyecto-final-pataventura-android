package com.example.pataventura.data.network.response

import com.google.gson.annotations.SerializedName

data class TutorResponse(
    @SerializedName("id_usuario") var idUsuario: Int,
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String,
    @SerializedName("telefono") var telefono: String,
    @SerializedName("nombre") var nombre: String,
    @SerializedName("apellido") var apellido: String,
    @SerializedName("imagen") var imagen: String,
    @SerializedName("alias") var alias: String,
    )