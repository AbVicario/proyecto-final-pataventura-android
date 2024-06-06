package com.example.pataventura.data.model

import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.Mascota
import com.example.pataventura.domain.model.Servicio
import com.example.pataventura.domain.model.Tutor

data class DemandaAceptadaModel(
    var idDemanda: Int = 0,
    var fechaInicio: String = "",
    var fechaFin: String = "",
    var descripcion: String = "",
    var precio: Double = 0.0,
    var estado: String = "",
    var oferta: Servicio = Servicio(),
    var mascota: Mascota = Mascota(),
    var tutor: Tutor = Tutor(),
    var cuidador: Cuidador = Cuidador()
)