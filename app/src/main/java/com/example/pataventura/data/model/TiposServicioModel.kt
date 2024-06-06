package com.example.pataventura.data.model

import com.google.gson.annotations.SerializedName

data class TiposServicioModel (
    @SerializedName("id_tipoOferta") var id_tipoOferta: Int,
    @SerializedName("tipo_oferta") var tipo_oferta: String,
    @SerializedName("kilometros") var kilometros: List<String>
)