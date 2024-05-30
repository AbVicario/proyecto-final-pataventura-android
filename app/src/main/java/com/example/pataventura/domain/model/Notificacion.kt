package com.example.pataventura.domain.model

import com.example.pataventura.data.model.NotificacionModel

data class Notificacion(
    var idAlerta: Int,
    var fechaCreacion: String = "",
    var estado: String = "",
    var descripcion: String = "",
    var demanda: Demanda,
    var tipo: String = "",
    var direccion: String = "",
    var mascotaName: String = ""
)

fun NotificacionModel.toDomain() = Notificacion(
    idAlerta,
    fechaCreacion,
    estado,
    descripcion,
    demanda,
    tipo,
    direccion,
    mascotaName
)

fun Notificacion.toModel() = NotificacionModel(
    idAlerta,
    fechaCreacion,
    estado,
    descripcion,
    demanda,
    tipo,
    direccion,
    mascotaName
)