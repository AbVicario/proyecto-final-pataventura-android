package com.example.pataventura.data.model

import com.example.pataventura.data.network.ApiUbicacion
import com.example.pataventura.domain.model.Servicio
import com.google.gson.annotations.SerializedName
import com.google.type.LatLng

class CuidadorModel(
    @SerializedName("id_usuario") var idUsuario: Int,
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String,
    @SerializedName("telefono") var telefono: String,
    @SerializedName("nombre") var nombre: String,
    @SerializedName("apellido") var apellido: String,
    @SerializedName("imagen") var imagen: ByteArray,
    @SerializedName("alias") var alias: String,
    @SerializedName("direccion") var direccion: String,
    @SerializedName("ubicacion") var ubicacion: LatLng? = null,
    @SerializedName("servicio") var servicio: Servicio? = null
)