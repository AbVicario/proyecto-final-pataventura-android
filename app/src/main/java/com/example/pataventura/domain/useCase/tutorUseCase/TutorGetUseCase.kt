package com.example.pataventura.domain.useCase.tutorUseCase

import com.example.pataventura.data.network.repository.TutorRepository
import com.example.pataventura.domain.model.Tutor
import javax.inject.Inject

class TutorGetUseCase@Inject constructor(
    private val tutorRepository: TutorRepository
) {
    suspend fun getTutor(): Tutor? {
        return tutorRepository.getTutorFromDatabase()
    }
}