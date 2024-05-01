package com.example.pataventura.domain.useCase.tutorUseCase

import com.example.pataventura.data.network.repository.TutorRepository
import javax.inject.Inject

class TutorUpdateUseCase@Inject constructor(
    private val tutorRepository: TutorRepository
) {
}