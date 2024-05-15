package com.example.pataventura.ui.screens.mascotas

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pataventura.domain.model.Mascota
import com.example.pataventura.domain.useCase.mascotaUseCase.GetMascotasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MascotasViewModel  @Inject constructor(
    private val getMascotasUseCase: GetMascotasUseCase
): ViewModel(){

    private val _mascotas = MutableLiveData<List<Mascota>>()
    val mascotas: MutableLiveData<List<Mascota>> = _mascotas

    suspend fun getMascotas(){
        viewModelScope.launch {
            val result = getMascotasUseCase.getMascotas()
            _mascotas.postValue(result)
        }
    }

}