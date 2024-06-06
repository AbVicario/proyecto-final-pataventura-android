package com.example.pataventura.data.network.service

import android.util.Log
import com.example.pataventura.data.model.TiposMascotaModel
import com.example.pataventura.data.network.ApiMascota
import javax.inject.Inject

class TiposMascotaService @Inject constructor(
    private val apiMascota: ApiMascota
) {
    suspend fun getTiposMascota(token: String): List<TiposMascotaModel> {
        return try {
            val tiposMascota = mutableListOf<TiposMascotaModel>()
            val response = apiMascota.getTiposMascota(token)
            Log.e("LOOK AT ME", "${response}")
            for (tiposMascotaResponse in response.data) {
                val tipoMascota = TiposMascotaModel(
                    tiposMascotaResponse.id_tipoMascota,
                    tiposMascotaResponse.tipo_mascota,
                    tiposMascotaResponse.razas
                )
                tiposMascota.add(tipoMascota)
            }
            tiposMascota
        } catch (e: Exception) {
            throw e
        }
    }
}