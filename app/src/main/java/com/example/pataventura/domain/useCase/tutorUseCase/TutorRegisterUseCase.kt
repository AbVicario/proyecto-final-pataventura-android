package com.example.pataventura.domain.useCase.tutorUseCase

import com.example.pataventura.data.network.repository.TutorRepository
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.domain.model.Tutor
import com.example.pataventura.domain.model.toEntity
import com.example.pataventura.domain.model.toModel
import javax.inject.Inject
class TutorRegisterUseCase@Inject constructor(
    private val tutorRepository: TutorRepository
) {
    suspend fun registroTutor(tutor: Tutor): CustomResponse {
        val response = tutorRepository.registerTutorFromApi(tutor.toModel())
        if(response.status == 200){
            tutor.idUsuario = response.data.toInt()
            tutorRepository.insertTutorToDatabase(tutor.toEntity())
        }
        return response
    }
}