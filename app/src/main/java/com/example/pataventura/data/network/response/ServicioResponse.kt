package com.example.pataventura.data.network.response

import com.google.gson.annotations.SerializedName

data class ServicioResponse (
    @SerializedName("data") val data: List<DataServicio>,
    @SerializedName("status") val status: Int,
    @SerializedName("ok") val ok: Boolean
)

data class DataServicio (
    @SerializedName("id_oferta") var idOferta: Int,
    @SerializedName("tipo") var tipo: String,
    @SerializedName("descripcion") var descripcion: String = "",
    @SerializedName("precio") var precio: Float,
    @SerializedName("radio") var radio: Int
)