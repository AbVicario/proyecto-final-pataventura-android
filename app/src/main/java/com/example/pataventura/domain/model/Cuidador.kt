package com.example.pataventura.domain.model

import com.example.pataventura.data.database.entity.CuidadorEntity
import com.example.pataventura.data.model.CuidadorModel

data class Cuidador(
    var idUsuario: Int,
    var email: String,
    var password: String,
    var telefono: String,
    var nombre: String,
    var apellido: String,
    var imagen: ByteArray,
    var alias: String,
    var direccion: String
)

fun CuidadorModel.toDomain() = Cuidador(
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
    direccion
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
