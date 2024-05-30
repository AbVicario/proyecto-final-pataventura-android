package com.example.pataventura.data.model

import com.example.pataventura.domain.model.Demanda
import com.google.gson.annotations.SerializedName

data class NotificacionModel(
    @SerializedName("id_alerta") val idAlerta: Int,
    @SerializedName("fechaCreacion") val fechaCreacion: String = "",
    @SerializedName("estado") val estado: String = "",
    @SerializedName("descripción") val descripcion: String = "",
    @SerializedName("demanda") val demanda: Demanda,
    @SerializedName("tipo") val tipo: String = "",
    @SerializedName("direcccion") val direccion: String = "",
    @SerializedName("mascotaName") val mascotaName: String = ""
)