package com.example.pataventura.domain.model

import com.example.pataventura.data.model.DemandaAceptadaModel

data class DemandaAceptada(
    var idDemanda: Int = 0,
    var fechaInicio: String = "",
    var fechaFin: String = "",
    var descripcion: String = "",
    var precio: Double = 0.0,
    var estado: String = "",
    var isValorada: Boolean = false,
    var oferta: Servicio = Servicio(),
    var mascota: Mascota = Mascota(),
    var tutor: Tutor = Tutor(),
    var cuidador: Cuidador = Cuidador()
) {
    constructor() : this(
        0, "", "", "",
        0.0, "", false, Servicio(), Mascota(), Tutor(), Cuidador()
    )
}

fun DemandaAceptadaModel.toDomain() = DemandaAceptada(
    idDemanda,
    fechaInicio,
    fechaFin,
    descripcion,
    precio,
    estado,
    isValorada,
    oferta,
    mascota,
    tutor,
    cuidador
)


fun DemandaAceptada.toModel() = DemandaAceptadaModel(
    idDemanda,
    fechaInicio,
    fechaFin,
    descripcion,
    precio,
    estado,
    isValorada,
    oferta,
    mascota,
    tutor,
    cuidador
)


