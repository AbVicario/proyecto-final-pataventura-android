package com.example.pataventura.domain.useCase.tiposUseCase

import com.example.pataventura.data.network.repository.TiposOfertaRepository
import com.example.pataventura.domain.model.TiposServicio
import com.example.pataventura.domain.model.toDomain
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import javax.inject.Inject

class GetTipoOfertaUseCase @Inject constructor(
    private val tipoOfertaRepository: TiposOfertaRepository,
    private val getTokenUseCase: TokenGetUseCase
) {
    suspend fun getTiposOferta(): List<TiposServicio> {
        return try {
            val token = getTokenUseCase.getToken().token
            val tiposOfertaModel = tipoOfertaRepository.getTiposOferta(token)
            val tiposOferta = tiposOfertaModel.map { it.toDomain() }
            tiposOferta
        } catch (e: Exception) {
            throw e
        }
    }
}