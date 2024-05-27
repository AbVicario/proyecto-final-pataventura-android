package com.example.pataventura.ui.screens.contratacion

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pataventura.di.RoleHolder
import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.Valoracion
import com.example.pataventura.domain.useCase.valoracionUseCase.GetValoracionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContratacionViewModel @Inject constructor(
    private val getValoracionesUseCase: GetValoracionUseCase

) : ViewModel(

) {
    private val _cuidador = MutableLiveData(Cuidador())
    val cuidador: MutableLiveData<Cuidador> = _cuidador

    private val _idCuidador = MutableLiveData<Int>()
    val idCuidador: LiveData<Int> = _idCuidador

    private val _valoraciones = MutableLiveData<List<Valoracion>>()
    val valoraciones: LiveData<List<Valoracion>> = _valoraciones

    fun setTrabajadorId(id: Int) {
        _idCuidador.postValue(id)
        viewModelScope.launch {
            getValoraciones(id)
        }
    }

    suspend fun setCuidador(cuidadores: List<Cuidador>) {
        try {
            viewModelScope.launch {
                for (cuidador in cuidadores) {
                    if (cuidador.idUsuario == idCuidador.value) {
                        _cuidador.postValue(cuidador)
                    }
                }
                getValoraciones(_cuidador.value!!.idUsuario)
            }
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getValoraciones(idCuidador: Int) {
        _valoraciones.postValue(getValoracionesUseCase.getValoraciones(idCuidador))
        Log.d("Valoraciones", _valoraciones.value.toString())
    }
}