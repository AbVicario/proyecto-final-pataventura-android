package com.example.pataventura.domain.useCase.tutorUseCase

import com.example.pataventura.data.database.dao.TokenDao
import com.example.pataventura.data.network.repository.TokenRepository
import com.example.pataventura.data.network.repository.TutorRepository
import com.example.pataventura.data.network.response.CuidadorResponse
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.data.network.response.TutorResponse
import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.Tutor
import com.example.pataventura.domain.model.toEntity
import com.example.pataventura.domain.model.toModel
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import javax.inject.Inject

class TutorUpdateUseCase @Inject constructor(
    private val tutorRepository: TutorRepository,
    private val tokenRepository: TokenRepository
) {
    suspend fun updateTutor(tutor: Tutor): TutorResponse {
        try{
            val token = tokenRepository.getTokenFromDatabase()
            val response = tutorRepository.updateTutorFromApi(token.token, tutor.toModel())
            if (response.status == 200) {
                tutor.idUsuario = response.data.idUsuario
                tutorRepository.updateTutorFromDatabase(tutor.toEntity())
            }
            return response
        }catch (e:Exception){
            throw e
        }

    }
}

