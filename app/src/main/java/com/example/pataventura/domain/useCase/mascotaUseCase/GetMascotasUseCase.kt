package com.example.pataventura.domain.useCase.mascotaUseCase

import android.util.Log
import com.example.pataventura.data.network.repository.MascotaRepository
import com.example.pataventura.domain.model.Mascota
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import javax.inject.Inject

class GetMascotasUseCase @Inject constructor(
    private val repository: MascotaRepository,
    private val tokenGetUseCase: TokenGetUseCase
) {
    suspend fun getMascotas(): List<Mascota> {
        return try {
            val token = tokenGetUseCase.getToken().token
            var listaMascotas = repository.getMascotasFromDatabase()
            Log.d("GetMascotasUseCase", "getMascotas: $listaMascotas")
            if (listaMascotas.isEmpty()) {
                listaMascotas = repository.getMascotasFromApi(token)
            }
            listaMascotas
        } catch (e: Exception) {
            throw e
        }
    }

}
