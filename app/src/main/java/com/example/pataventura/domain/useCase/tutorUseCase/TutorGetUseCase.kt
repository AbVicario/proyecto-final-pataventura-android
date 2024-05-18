package com.example.pataventura.domain.useCase.tutorUseCase

import android.util.Log
import com.example.pataventura.data.network.repository.TutorRepository
import com.example.pataventura.data.network.repository.TokenRepository
import com.example.pataventura.domain.model.Tutor
import javax.inject.Inject

class TutorGetUseCase @Inject constructor(
    private val tutorRepository: TutorRepository,
    private val tokenRepository: TokenRepository
) {
    suspend fun getTutor(): Tutor? {
        val token = tokenRepository.getTokenFromDatabase()
        Log.e("Tutor", token.token)

        try {
            var tutor = tutorRepository.getTutorFromDatabase()
            if (tutor == null) {
                tutor = tutorRepository.getTutorFromApi(token.token)
                Log.e("Tutor", "api")
                return tutor

            } else {
                return tutor
            }

        } catch (e: Exception) {
            Log.e("Tutor", e.toString())
            throw e
        }


    }
}