package com.example.pataventura.domain.model

import com.example.pataventura.data.model.CuidadorModel
import com.example.pataventura.data.model.ValoracionModel

data class Valoracion (
    var idValoracion : Int,
    var puntuacion : Double,
    var descripcion : String,
    var aliasTutor : String,
    var imagenTutor : ByteArray
)

fun ValoracionModel.toDomain() = Valoracion(
    idValoracion,
    puntuacion,
    descripcion,
    aliasTutor,
    imagenTutor
)

fun Valoracion.toModel() = ValoracionModel(
    idValoracion,
    puntuacion,
    descripcion,
    aliasTutor,
    imagenTutor
)