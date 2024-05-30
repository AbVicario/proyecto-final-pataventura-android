package com.example.pataventura.ui.screens.notificaciones

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pataventura.domain.model.Demanda
import com.example.pataventura.domain.model.Mascota
import com.example.pataventura.domain.model.Notificacion
import com.example.pataventura.domain.useCase.demandaUseCase.DemandaUpdateUseCase
import com.example.pataventura.domain.useCase.mascotaUseCase.MascotaGetUseCase
import com.example.pataventura.domain.useCase.notificacionUseCase.GetNotificacionesUseCase
import com.example.pataventura.domain.useCase.notificacionUseCase.UpdateNotificacionesUseCase
import com.example.pataventura.ui.screens.login.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificacionesViewModel @Inject constructor(
    private val updateNotificacionUseCase: UpdateNotificacionesUseCase,
    private val getMascotaUseCase: MascotaGetUseCase,
    private val updateDemandaUseCase: DemandaUpdateUseCase,
    private val getNotificacionesUseCase: GetNotificacionesUseCase

) : ViewModel() {

    private val _mascota = MutableLiveData<Mascota>()
    val mascota: MutableLiveData<Mascota> = _mascota


    fun updateNotificacion(notificacion: Notificacion ) {
        viewModelScope.launch {
            updateNotificacionUseCase.updateNotificacion(notificacion)
        }
    }
    fun updateDemanda(demanda: Demanda ) {
        viewModelScope.launch {
            updateDemandaUseCase.updateDemanda(demanda)
        }
    }


    fun getMascota(idMascota: Int) {
        viewModelScope.launch {
            _mascota.postValue(getMascotaUseCase.getMascota(idMascota))
        }
    }

    fun actualizarLista(loginViewModel: LoginViewModel, notificacion: Notificacion) {
        val listaNotificaciones = loginViewModel.notificaciones.value.orEmpty().toMutableList()
        if (listaNotificaciones.isNotEmpty()) {
            if (notificacion.estado.lowercase() == "borrada") {
                listaNotificaciones.removeAll { it.idAlerta == notificacion.idAlerta }
                loginViewModel.onNotificacionesChange(listaNotificaciones)
            } else {
                if (notificacion.estado.lowercase() == "leida") {
                    val listaAux = mutableListOf<Notificacion>()
                    for (item in listaNotificaciones) {
                        if (item.idAlerta == notificacion.idAlerta) {
                            item.estado = "leida"
                        }
                        listaAux.add(item)
                    }
                    loginViewModel.onNotificacionesChange(listaAux)
                }
            }

        }
    }

}