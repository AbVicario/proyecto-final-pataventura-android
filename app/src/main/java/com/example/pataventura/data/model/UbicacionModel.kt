package com.example.pataventura.data.model

import com.google.gson.annotations.SerializedName
import com.google.type.LatLng

data class UbicacionModel (
    @SerializedName("id_ubicacion") var idUbicacion: Int,
    @SerializedName("coordenadas") var coordenadas: String
)