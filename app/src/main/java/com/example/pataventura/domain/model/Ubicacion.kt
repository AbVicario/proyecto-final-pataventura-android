package com.example.pataventura.domain.model

import com.example.pataventura.data.model.UbicacionModel

class Ubicacion (
    var idUbicacion: Int,
    var coordenadas: String
)


fun Ubicacion.toModel() = UbicacionModel(
    idUbicacion,
    coordenadas
)