package com.example.pataventura.domain.model

import com.example.pataventura.data.model.UbicacionModel
import com.google.type.LatLng

class Ubicacion (
    var idUbicacion: Int,
    var coordenadas: String
)

fun Ubicacion.toModel() = UbicacionModel(
    idUbicacion,
    coordenadas
)