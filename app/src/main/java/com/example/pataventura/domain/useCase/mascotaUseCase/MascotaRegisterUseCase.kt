package com.example.pataventura.domain.useCase.mascotaUseCase

import android.util.Log
import com.example.pataventura.data.network.repository.MascotaRepository
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.domain.model.Mascota
import com.example.pataventura.domain.model.toEntity
import com.example.pataventura.domain.model.toModel
import com.example.pataventura.domain.useCase.tutorUseCase.TutorGetUseCase
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import javax.inject.Inject

class MascotaRegisterUseCase@Inject constructor(
    private val mascotaRepository: MascotaRepository,
    private val tokenGetUseCase: TokenGetUseCase,
    private val tutorGetUseCase: TutorGetUseCase
) {
    suspend fun registroMascota(mascota: Mascota
    ): CustomResponse {
        val token = tokenGetUseCase.getToken().token
        val response = mascotaRepository.registerMascotaFromApi(
            token, mascota.toModel())
        if(response.status == 200){
            mascotaRepository.insertMascotaToDatabase(mascota.toEntity())
        }
        return response
    }
}