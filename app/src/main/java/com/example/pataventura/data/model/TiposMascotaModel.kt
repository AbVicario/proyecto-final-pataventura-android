package com.example.pataventura.data.model

import com.google.gson.annotations.SerializedName

class TiposMascotaModel (
    @SerializedName("id_tipoMascota") var id_tipoMascota: Int,
    @SerializedName("tipo_mascota") var tipo_mascota: String,
    @SerializedName("razas") var razas: List<String>
)