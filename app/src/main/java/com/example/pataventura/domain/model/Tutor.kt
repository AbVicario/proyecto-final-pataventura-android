package com.example.pataventura.domain.model

import com.example.pataventura.data.database.entity.TutorEntity
import com.example.pataventura.data.model.TutorModel

class Tutor (
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

fun TutorModel.toDomain() = Tutor(
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

fun TutorEntity.toDomain() = Tutor(
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

fun Tutor.toModel() = TutorModel(
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

fun Tutor.toEntity() = TutorEntity(
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