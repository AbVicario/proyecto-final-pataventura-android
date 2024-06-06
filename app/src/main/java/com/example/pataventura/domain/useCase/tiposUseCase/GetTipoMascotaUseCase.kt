package com.example.pataventura.domain.useCase.tiposUseCase

import com.example.pataventura.data.network.repository.TiposMascotaRepository
import com.example.pataventura.domain.model.TiposMascota
import com.example.pataventura.domain.model.toDomain
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import javax.inject.Inject

class GetTipoMascotaUseCase @Inject constructor(
    private val tipoMascotaRepository: TiposMascotaRepository,
    private val getTokenUseCase: TokenGetUseCase
) {
    suspend fun getTiposMascota(): List<TiposMascota> {
        return try {
            val token = getTokenUseCase.getToken().token
            var tiposMascotaModel = tipoMascotaRepository.getTiposMascota(token)
            var tiposMascota = tiposMascotaModel.map { it.toDomain() }
            tiposMascota
        } catch (e: Exception) {
            throw e

        }
    }
}