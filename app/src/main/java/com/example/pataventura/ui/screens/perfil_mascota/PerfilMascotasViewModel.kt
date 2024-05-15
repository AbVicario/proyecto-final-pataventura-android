package com.example.pataventura.ui.screens.perfil_mascota

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PerfilMascotaViewModel @Inject constructor() : ViewModel(){

    private val _raza = MutableLiveData<String>()
    val raza: LiveData<String> = _raza

    private val _sexo = MutableLiveData<String>()
    val sexo: LiveData<String> = _sexo

    private val _image = MutableLiveData<ImageBitmap>()
    val image: LiveData<ImageBitmap> = _image

    fun onRazaChange(raza: String) {
        _raza.postValue(raza)
    }

    fun onSexoChange(sexo: String) {
        _sexo.postValue(sexo)
    }
}