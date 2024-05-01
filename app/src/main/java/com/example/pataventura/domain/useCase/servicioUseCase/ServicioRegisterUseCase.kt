package com.example.pataventura.domain.useCase.servicioUseCase
import com.example.pataventura.data.network.repository.ServicioRepository
import com.example.pataventura.data.network.response.CustomResponse
import com.example.pataventura.domain.model.Servicio
import com.example.pataventura.domain.model.toEntity
import com.example.pataventura.domain.model.toModel
import com.example.pataventura.domain.useCase.cuidadorUseCase.CuidadorGetUseCase
import com.example.pataventura.domain.useCase.tokenUseCase.TokenGetUseCase
import javax.inject.Inject

class ServicioRegisterUseCase@Inject constructor(
    private val servicioRepository: ServicioRepository,
    private val tokenGetUseCase: TokenGetUseCase,
    private val cuidadorGetUseCase: CuidadorGetUseCase
) {
    suspend fun registroServicio(servicio: Servicio,
                                 ): CustomResponse {
        val token = tokenGetUseCase.getToken().token
        val idCuidador = cuidadorGetUseCase.getCuidador()!!.idUsuario
        val response = servicioRepository.registerServicioFromApi(
            token, servicio.toModel(), idCuidador)
        if(response.status == 200){
            servicioRepository.insertServicioToDatabase(servicio.toEntity())
        }
        return response
    }
}