package com.example.pataventura.ui.screens.calendario

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pataventura.di.RoleHolder
import com.example.pataventura.domain.model.DemandaAceptada
import com.example.pataventura.domain.useCase.GetDemandasAceptadasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CalendarioViewModel @Inject constructor(
    private val getDemandasAceptadasUseCase: GetDemandasAceptadasUseCase
) : ViewModel() {
    private val _demandas = MutableLiveData<List<DemandaAceptada>>()
    val demandas: MutableLiveData<List<DemandaAceptada>> = _demandas


    fun getDemandasAceptadas() {
        viewModelScope.launch {
            val result = getDemandasAceptadasUseCase.getDemandasAceptadas(RoleHolder.rol.value.toString().lowercase())
            _demandas.value = result
        }
    }
}