package com.example.pataventura.domain.model

import com.example.pataventura.data.database.entity.CuidadorEntity
import com.example.pataventura.data.model.CuidadorModel
import com.google.type.LatLng
import org.json.JSONArray

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
    var servicio: Servicio? = null
) {
    constructor() : this(0, "", "", "", "", "", byteArrayOf(), "", "", null, null)
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
    servicio
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
