package com.example.pataventura.domain.model

data class Demanda(
    var id_demanda: Int,
    var fechaInicio: String = "",
    var fechaFin: String? = "",
    var precio: Double? =0.0,
    var descripcion: String? = "",
    var estado: String? = "",
    var id_mascota: Int=0,
    var id_servicio: Int=0
)