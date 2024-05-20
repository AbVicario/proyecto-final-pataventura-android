package com.example.pataventura.domain.model

import com.example.pataventura.data.database.entity.ServicioEntity
import com.example.pataventura.data.model.ServicioModel

data class Servicio(
    var idOferta: Int = 0,
    var tipo: String = "",
    var descripcion: String = "",
    var precio: Float = 0.0f,
    var radio: Int = 0
)

fun ServicioModel.toDomain() = Servicio(
    idOferta,
    tipo,
    descripcion,
    precio,
    radio
)

fun ServicioEntity.toDomain() = Servicio(
    idOferta,
    tipo,
    descripcion,
    precio,
    radio
)

fun Servicio.toModel() = ServicioModel(
    idOferta,
    tipo,
    descripcion,
    precio,
    radio
)

fun Servicio.toEntity() = ServicioEntity(
    idOferta,
    tipo,
    descripcion,
    precio,
    radio
)