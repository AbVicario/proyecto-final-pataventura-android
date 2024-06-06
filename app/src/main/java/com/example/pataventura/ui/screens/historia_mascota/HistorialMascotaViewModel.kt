package com.example.pataventura.ui.screens.historia_mascota

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pataventura.di.RoleHolder
import com.example.pataventura.domain.model.DemandaAceptada
import com.example.pataventura.domain.useCase.GetDemandasRealizadasUseCase
import com.example.pataventura.domain.useCase.valoracionUseCase.RegisterValoracionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistorialMascotaViewModel @Inject constructor(
    private val registerValoracionUseCase: RegisterValoracionUseCase,
    private val getDemandasRealizadasUseCase: GetDemandasRealizadasUseCase
) : ViewModel() {

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: MutableLiveData<Boolean> = _showDialog

    private val _idDemanda = MutableLiveData<Int>()
    val idDemanda: MutableLiveData<Int> = _idDemanda

    private val _demandasRealizadas = MutableLiveData<List<DemandaAceptada>>()
    val demandasRealizadas: MutableLiveData<List<DemandaAceptada>> = _demandasRealizadas

    val comentario = MutableLiveData<String>()
    val valoracion = MutableLiveData<Int>()
    fun showDialog() {
        _showDialog.value = true
    }

    fun hideDialog() {
        _showDialog.value = false
    }

    fun onComentarioChange(it: String) {
        comentario.postValue(it)
    }

    fun onValoracionChange(rating: Int) {
        valoracion.postValue(rating)
    }

    fun getDemandasAceptadas() {
        var rol = RoleHolder.rol.value.toString().lowercase()
        viewModelScope.launch {
            val result = getDemandasRealizadasUseCase.getDemandasRealizadas(rol)
            _demandasRealizadas.postValue(result)
        }
    }
    fun registerValoracion(context: Context) {
        viewModelScope.launch {
            val result =
                registerValoracionUseCase.registerValoracion(
                    comentario.value!!,
                    valoracion.value!!,
                    idDemanda.value!!
                )
            if(result.status == 200){
                Toast.makeText(context,"Valoracion registrada", Toast.LENGTH_SHORT).show()
            }
        }
    }
}