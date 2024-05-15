package com.example.pataventura.ui.screens.registoMascota

import android.content.Context
import android.database.Observable
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.pataventura.core.navigations.Destinations
import com.example.pataventura.domain.model.Mascota
import com.example.pataventura.domain.useCase.mascotaUseCase.MascotaRegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroMascotaViewModel @Inject constructor(
    private val mascotaRegisterUseCase: MascotaRegisterUseCase
) : ViewModel() {
    var showDialog by mutableStateOf(false)
        private set

    private val _nombreEmpty = MutableLiveData<Boolean>()
    val nombreEmpty: LiveData<Boolean> = _nombreEmpty
    private val _tipoEmpty = MutableLiveData<Boolean>()
    val tipoEmpty: LiveData<Boolean> = _tipoEmpty
    private val _numChipEmpty = MutableLiveData<Boolean>()
    val numChipEmpty: LiveData<Boolean> = _numChipEmpty
    private val _colorEmpty = MutableLiveData<Boolean>()
    val colorEmpty: LiveData<Boolean> = _colorEmpty
    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> = _nombre
    private val _tipo = MutableLiveData<String>()
    val tipo: LiveData<String> = _tipo
    private val _raza = MutableLiveData<String>()
    val raza: LiveData<String> = _raza
    private val _edad = MutableLiveData<String>()
    val edad: LiveData<String> = _edad
    private val _peso = MutableLiveData<String>()
    val peso: LiveData<String> = _peso
    private val _descripcion = MutableLiveData<String>()
    val descripcion: LiveData<String> = _descripcion
    private val _listaRaza: ObservableList<String> = ObservableArrayList<String>().apply {
        add("Campo Vacio")
    }
    val listaRaza: ObservableList<String> = _listaRaza
    private val _numChip = MutableLiveData<String>()
    val numChip: LiveData<String> = _numChip
    private val _colorAsig = MutableLiveData<String>()
    val colorAsig: LiveData<String> = _colorAsig
    private val _isTipoElegido = MutableLiveData<Boolean>()
    val isTipoElegido: LiveData<Boolean> = _isTipoElegido


    var itemsRazaGato = listOf<String>(
        "Siames",
        "Europeo",
        " "
    )
    var itemsRazaPerro = listOf<String>(
        "Bichón",
        "Pekines",
        " "
    )


    fun pintarItemsRaza(tipo: String) {
        val itemsToAdd: MutableList<String> = when (tipo) {
            "Perro" -> itemsRazaPerro.toMutableList()
            "Gato" -> itemsRazaGato.toMutableList()
            else -> itemsRazaGato.toMutableList()
        }
        _listaRaza.clear()
        _listaRaza.addAll(itemsToAdd)
    }

    fun onNombreChange(nombre: String) {
        _nombre.postValue(nombre)
    }

    fun onTipoChange(tipo: String) {
        _tipo.postValue(tipo)
        pintarItemsRaza(tipo)
    }

    fun onRazaChange(raza: String) {
        _raza.postValue(raza)
    }

    fun onEdadChange(edad: String) {
        _edad.postValue(edad)
    }

    fun onPesoChange(peso: String) {
        _peso.postValue(peso)
    }

    fun onDescripcionChange(descripcion: String) {
        _descripcion.postValue(descripcion)
    }

    fun onNumChipChange(numChip: String) {
        _numChip.postValue(numChip)
    }

    fun oncolorAsigChange(colorAsig: String) {
        _colorAsig.postValue(colorAsig)
    }

    fun onDialogConfirm(navController: NavController) {
        showDialog = false
        navController.navigate(route = Destinations.Home.route)

    }

    fun onOpenDialog() {
        showDialog = true
    }

    fun onFinalizarPress(navController: NavController, context: Context) {
        viewModelScope.launch {
            val nombre = _nombre.value
            val numChip = _numChip.value
            val tipo = _tipo.value
            val colorAsig = _colorAsig.value
            _nombreEmpty.postValue(false)
            _numChipEmpty.postValue(false)
            _tipoEmpty.postValue(false)
            _colorEmpty.postValue(false)

            if (nombre.isNullOrBlank()) {
                _nombreEmpty.postValue(true)
            }
            if (numChip.isNullOrBlank()) {
                _numChipEmpty.postValue(true)
            }
            if (tipo.isNullOrBlank()) {
                _tipoEmpty.postValue(true)
            }
            if (colorAsig.isNullOrBlank()) {
                _colorEmpty.postValue(true)
            }
            if (_colorEmpty.value == false && _nombreEmpty.value == false && _numChipEmpty.value == false
                && _tipoEmpty.value == false
            ) {

                val mascota = Mascota(
                    0, _nombre.value!!, _numChip.value!!,
                    _edad.value!!, "", tamanyo = 0.0, peso = 0.0, _tipo.value!!,
                    _raza.value!!, observacion = "", _colorAsig.value!!
                )
                val response = mascotaRegisterUseCase.registroMascota(mascota)
                if (response.ok) {
                    Log.d("RegistroMascotaViewModel", "respuesta: $response")
                    Toast.makeText(context, "Mascota registrada", Toast.LENGTH_SHORT).show()
                    navController.navigate(route = Destinations.Home.route)
                } else {
                    onOpenDialog()
                }
            }
        }
    }

}