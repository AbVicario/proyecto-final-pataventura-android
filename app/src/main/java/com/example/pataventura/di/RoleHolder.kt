package com.example.pataventura.di

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object RoleHolder {
    private val _rol = MutableLiveData<String>()
    val rol: LiveData<String> = _rol

    fun setRol(value: String) {
        _rol.postValue(value)
    }

}

object IdCuidador {
    private val _idCuidador = MutableLiveData<Int>()
    val idCuidador: LiveData<Int> = _idCuidador

    fun setIdCuidador(value: Int) {
        _idCuidador.postValue(value)
    }

}

object NotificacionSize {
    private val _notificacionSize = MutableLiveData<Int>(0)
    val notificacionSize: LiveData<Int> = _notificacionSize

    fun setNotificacionSize(value: Int) {
        _notificacionSize.postValue(value)
    }

}