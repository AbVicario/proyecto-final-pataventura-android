package com.example.pataventura.domain.model

import com.google.type.DateTime

data class Demanda(
    var idDemanda: Int,
    var fechaInicio: DateTime,
    var fechaFin: DateTime,
    var precio: Double,
    var descripcion: String,
    var estado: String
)