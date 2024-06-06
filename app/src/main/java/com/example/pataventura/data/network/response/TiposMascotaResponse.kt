package com.example.pataventura.data.network.response

import com.google.gson.annotations.SerializedName

data class TiposMascotaResponse(
    @SerializedName("data") val data: List<DataTiposMascota>,
    @SerializedName("status") val status: Int,
    @SerializedName("ok") val ok: Boolean
)

data class DataTiposMascota(
    @SerializedName("id_tipoMascota") val id_tipoMascota: Int,
    @SerializedName("tipo_mascota") val tipo_mascota: String,
    @SerializedName("razas") val razas: List<String>
)