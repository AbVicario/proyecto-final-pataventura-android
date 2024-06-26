package com.example.pataventura.domain.model

import com.example.pataventura.data.database.entity.MascotaEntity
import com.example.pataventura.data.model.MascotaModel

data class Mascota(
    var idMascota: Int,
    var nombre: String,
    var numChip: String,
    var edad: String = "",
    var imagen: ByteArray? = null,
    var tamanyo: String = "",
    var peso: Double = 0.0,
    var tipo: String,
    var raza: String = "",
    var observacion: String = "",
    var color: String,
    var sexo: String = ""
)
fun MascotaModel.toDomain() = Mascota(
    idMascota,
    nombre,
    numChip,
    edad,
    imagen,
    tamanyo,
    peso,
    tipo,
    raza,
    observacion,
    color,
    sexo
)
fun MascotaEntity.toDomain() = Mascota(
    idMascota,
    nombre,
    numChip,
    edad,
    imagen,
    tamanyo,
    peso,
    tipo,
    raza,
    observacion,
    color,
    sexo
)

fun Mascota.toModel() = MascotaModel(
    idMascota,
    nombre,
    numChip,
    edad,
    imagen,
    tamanyo,
    peso,
    tipo,
    raza,
    observacion,
    color,
    sexo
)
fun Mascota.toEntity() = MascotaEntity(
    idMascota,
    nombre,
    numChip,
    edad,
    imagen,
    tamanyo,
    peso,
    tipo,
    raza,
    observacion,
    color,
    sexo
)