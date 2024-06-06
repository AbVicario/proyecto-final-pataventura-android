package com.example.pataventura.data.network.response

import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.Mascota
import com.example.pataventura.domain.model.Servicio
import com.example.pataventura.domain.model.Tutor
import com.google.gson.annotations.SerializedName

data class DemandaAceptadaReponse(
    @SerializedName("data") val data: List<DataDemandaAceptada>,
    @SerializedName("status") val status: Int,
    @SerializedName("ok") val ok: Boolean
) {
}

data class DataDemandaAceptada(
    @SerializedName("id_demanda") val idDemanda: Int = 0,
    @SerializedName("fechaInicio") val fechaInicio: String = "",
    @SerializedName("fechaFin") val fechaFin: String = "",
    @SerializedName("descripcion") val descripcion: String = "",
    @SerializedName("precio") val precio: Double = 0.0,
    @SerializedName("estado") val estado: String = "",
    @SerializedName("isValorada") val isValorada: Boolean = false,
    @SerializedName("oferta") val oferta: Servicio = Servicio(),
    @SerializedName("mascota") val mascota: Mascota = Mascota(),
    @SerializedName("tutor") val tutor: Tutor = Tutor(),
    @SerializedName("cuidador") val cuidador: Cuidador = Cuidador()
)
