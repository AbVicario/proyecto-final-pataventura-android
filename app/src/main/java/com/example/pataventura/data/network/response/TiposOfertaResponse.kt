package com.example.pataventura.data.network.response

import com.google.gson.annotations.SerializedName

data class TiposOfertaResponse(
    @SerializedName("data") val data: List<DataTiposOferta>,
    @SerializedName("status") val status: Int,
    @SerializedName("ok") val ok: Boolean
)

data class DataTiposOferta(
    @SerializedName("id_tipoOferta") val id_tipoOferta: Int,
    @SerializedName("tipo_oferta") val tipo_oferta: String,
    @SerializedName("kilometros") val kilometros: List<String>
)