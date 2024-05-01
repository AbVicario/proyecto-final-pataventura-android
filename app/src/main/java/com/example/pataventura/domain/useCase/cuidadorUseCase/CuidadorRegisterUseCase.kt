package com.example.pataventura.domain.useCase.cuidadorUseCase

import com.example.pataventura.data.network.repository.CuidadorRepository
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.toEntity
import com.example.pataventura.domain.model.toModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
class CuidadorRegisterUseCase @Inject constructor(
    private val cuidadorRepository: CuidadorRepository
) {
    suspend fun registroCuidador(cuidador: Cuidador): CustomResponse{
        val response = cuidadorRepository.registerCuidadorFromApi(cuidador.toModel())
        if(response.status == 200){
            cuidador.idUsuario = response.data.toInt()
            cuidadorRepository.insertCuidadorToDatabase(cuidador.toEntity())
        }
        return response
    }
}
