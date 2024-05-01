package com.example.pataventura.data.model
import com.google.gson.annotations.SerializedName

data class ServicioModel(
    @SerializedName("id_oferta") var idOferta: Int,
    @SerializedName("tipo") var tipo: String,
    @SerializedName("descripcion") var descripcion: String = "",
    @SerializedName("precio") var precio: Float,
    @SerializedName("radio") var radio: Int
)