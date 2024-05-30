package com.example.pataventura.data.network.response

import com.example.pataventura.domain.model.Demanda
import com.google.gson.annotations.SerializedName

data class NotificacionResponse(
    @SerializedName("data") val data: List<DataNotificacion>,
    @SerializedName("status") val status: Int,
    @SerializedName("ok") val ok: Boolean
)

data class DataNotificacion(
    @SerializedName("id_alerta") val idAlerta: Int,
    @SerializedName("fechaCreacion") val fechaCreacion: String = "",
    @SerializedName("estado") val estado: String = "",
    @SerializedName("descripcion") val descripcion: String = "",
    @SerializedName("demanda") val demanda: Demanda,
    @SerializedName("tipo") val tipoOferta: String = "",
    @SerializedName("direccion") val direccion: String = "",
    @SerializedName("mascotaName") val mascotaName: String = "",
)


