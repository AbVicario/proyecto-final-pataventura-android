package com.example.pataventura.domain.model

import com.example.pataventura.data.model.TiposServicioModel

class TiposServicio(
    var id_tipoOferta: Int,
    var tipo_oferta: String,
    var kilometros: List<String>
)

fun TiposServicioModel.toDomain() = TiposServicio(
    id_tipoOferta,
    tipo_oferta,
    kilometros
)

fun TiposServicio.toModel() = TiposServicioModel(
    id_tipoOferta,
    tipo_oferta,
    kilometros
)
