package com.example.pataventura.ui.screens.historia_mascota

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.pataventura.domain.converters.ImageConverter
import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.DemandaAceptada
import com.example.pataventura.domain.useCase.GetDemandasRealizadasCuidadorUseCase
import com.example.pataventura.domain.useCase.cuidadorUseCase.CuidadorGetUseCase
import com.example.pataventura.domain.useCase.valoracionUseCase.RegisterValoracionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistorialCuidadorViewModel @Inject constructor(
    private val registerValoracionUseCase: RegisterValoracionUseCase,
    private val getDemandasRealizadasCuidadorUseCase: GetDemandasRealizadasCuidadorUseCase,
    private val getCuidadorUseCase: CuidadorGetUseCase
) : ViewModel() {
    private val _cuidador = MutableLiveData<Cuidador>()
    val cuidador: MutableLiveData<Cuidador> = _cuidador

    private val _nombre = MutableLiveData<String>()
    val nombre: MutableLiveData<String> = _nombre

    private val _imagen = MutableLiveData<ImageBitmap>()
    val imagen: MutableLiveData<ImageBitmap> = _imagen

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: MutableLiveData<Boolean> = _showDialog

    private val _idDemanda = MutableLiveData<Int>()
    val idDemanda: MutableLiveData<Int> = _idDemanda


    private val _nombreCuidador = MutableLiveData<String>()
    val nombreCuidador: MutableLiveData<String> = _nombreCuidador

    private val _fechaInicio = MutableLiveData<String>()
    val fechaInicio: MutableLiveData<String> = _fechaInicio

    private val _fechaFin = MutableLiveData<String>()
    val fechaFin: MutableLiveData<String> = _fechaFin

    private val _precio = MutableLiveData<String>()
    val precio: MutableLiveData<String> = _precio

    private val _imagenCuidador = MutableLiveData<ImageBitmap>()
    val imagenCuidador: MutableLiveData<ImageBitmap> = _imagenCuidador

    private val _servicio = MutableLiveData<String>()
    val servicio: MutableLiveData<String> = _servicio

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

    fun registerValoracion(context: Context, navController: NavController) {
        viewModelScope.launch {
            if (comentario.value.isNullOrEmpty()) {
                Toast.makeText(context, "Ingrese un comentario", Toast.LENGTH_LONG).show()
            } else {
                val result =
                    registerValoracionUseCase.registerValoracion(
                        comentario.value!!,
                        valoracion.value!!,
                        idDemanda.value!!
                    )
                if (result.status == 200) {
                    Toast.makeText(context, "Valoracion registrada", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                } else {
                    Toast.makeText(context, "Error al registrar valoracion", Toast.LENGTH_SHORT)
                        .show()
                }
                hideDialog()
                valoracion.postValue(1)
                comentario.postValue("")
            }
        }
    }
    fun setIdCuidador() {
        viewModelScope.launch {
            val result = getDemandasRealizadasCuidadorUseCase.getDemandasRealizadas()
            _demandasRealizadas.postValue(result)
            val cuidador = getCuidadorUseCase.getCuidador()
            if (cuidador != null) {
                _cuidador.postValue(cuidador!!)
                _nombre.postValue(cuidador.nombre)
                _imagen.postValue(ImageConverter.byteArrayToImageBitmap(cuidador.imagen!!))
            }
        }
    }
}