package com.example.pataventura.domain.model

import androidx.compose.ui.graphics.Color
import com.example.pataventura.data.database.entity.MascotaEntity
import com.example.pataventura.data.model.MascotaModel

data class Mascota(
    var idMascota: Int,
    var nombre: String,
    var numChip: String,
    var edad: String = "",
    var imagen: String = "",
    var tamanyo: Float,
    var peso: Float,
    var tipo: String,
    var raza: String = "",
    var observacion: String = "",
    var color: String
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
    color
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
    color
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
    color
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
    color
)