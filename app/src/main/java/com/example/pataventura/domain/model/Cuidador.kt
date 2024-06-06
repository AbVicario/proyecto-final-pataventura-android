package com.example.pataventura.domain.model

import com.example.pataventura.data.database.entity.CuidadorEntity
import com.example.pataventura.data.model.CuidadorModel
import com.google.type.LatLng

data class Cuidador(
    var idUsuario: Int,
    var email: String,
    var password: String,
    var telefono: String,
    var nombre: String,
    var apellido: String,
    var imagen: ByteArray,
    var alias: String,
    var direccion: String,
    var ubicacion: LatLng? = null,
    var servicio: Servicio? = null,
    var valoraciones: List<Valoracion>? = emptyList(),
) {
    constructor() : this(0, "", "", "", "", "", byteArrayOf(), "", "", null, Servicio(), emptyList())
}

fun CuidadorModel.toDomain() = Cuidador(
    idUsuario,
    email,
    password,
    telefono,
    nombre,
    apellido,
    imagen,
    alias,
    direccion,
    ubicacion,
    servicio,
    valoraciones
)

fun CuidadorEntity.toDomain() = Cuidador(
    idUsuario,
    email,
    password,
    telefono,
    nombre,
    apellido,
    imagen,
    alias,
    direccion
)

fun Cuidador.toModel() = CuidadorModel(
    idUsuario,
    email,
    password,
    telefono,
    nombre,
    apellido,
    imagen,
    alias,
    direccion,
    ubicacion,
    servicio,
    valoraciones
)

fun Cuidador.toEntity() = CuidadorEntity(
    idUsuario,
    email,
    password,
    telefono,
    nombre,
    apellido,
    imagen,
    alias,
    direccion
)
