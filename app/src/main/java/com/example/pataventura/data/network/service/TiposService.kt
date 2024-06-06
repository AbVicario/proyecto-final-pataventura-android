package com.example.pataventura.data.network.service

import android.util.Log
import com.example.pataventura.data.model.TiposServicioModel
import com.example.pataventura.data.network.ApiServicio
import javax.inject.Inject

class TiposOfertaService@Inject constructor(
    private val apiServicio: ApiServicio
){
    suspend fun getTiposOferta(token: String): List<TiposServicioModel> {
        return try {
            val servicios = mutableListOf<TiposServicioModel>()
            val response = apiServicio.getTiposOferta(token)
            Log.e("LOOK AT ME", "${response}")
            for (servicioResponse in response.data) {
                val servicio = TiposServicioModel(
                    servicioResponse.id_tipoOferta,
                    servicioResponse.tipo_oferta,
                    servicioResponse.kilometros
                )
                servicios.add(servicio)
            }
            servicios
        } catch (e: Exception) {
            throw e
        }
    }
}
