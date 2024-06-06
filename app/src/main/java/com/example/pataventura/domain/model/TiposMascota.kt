package com.example.pataventura.domain.model

import com.example.pataventura.data.model.TiposMascotaModel

data class TiposMascota(
    var id_tipoMascota: Int,
    var tipo_mascota: String,
    var razas: List<String>
)

fun TiposMascotaModel.toDomain() = TiposMascota(
    id_tipoMascota,
    tipo_mascota,
    razas
)

fun TiposMascota.toModel() = TiposMascotaModel(
    id_tipoMascota = id_tipoMascota,
    tipo_mascota = tipo_mascota,
    razas = razas
)