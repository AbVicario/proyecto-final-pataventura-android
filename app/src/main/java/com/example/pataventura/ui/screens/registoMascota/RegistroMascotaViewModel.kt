package com.example.pataventura.ui.screens.registoMascota

import android.database.Observable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroMascotaViewModel @Inject constructor(
) : ViewModel() {

    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> = _nombre
    private val _tipo = MutableLiveData<String>()
    val tipo: LiveData<String> = _tipo
    private val _raza = ObservableField<String>("")
    val raza: ObservableField<String>
        get() = _raza
    private val _edad = MutableLiveData<String>()
    val edad: LiveData<String> = _edad
    private val _peso = MutableLiveData<String>()
    val peso: LiveData<String> = _peso
    private val _descripcion = MutableLiveData<String>()
    val descripcion: LiveData<String> = _descripcion
    private val _listaRaza: ObservableList<String> = ObservableArrayList<String>().apply {
        add("Campo Vacio")}
    val listaRaza: ObservableList<String> = _listaRaza
    private val _numChip = MutableLiveData<String>()
    val numChip: LiveData<String> = _numChip
    private val _colorAsig = MutableLiveData<String>()
    val colorAsig: LiveData<String> = _colorAsig
    private val _isTipoElegido = MutableLiveData<Boolean>()
    val isTipoElegido: LiveData<Boolean> = _isTipoElegido


    var itemsRazaGato = listOf<String>(
        "Siames",
        "Europeo"
    )
    var itemsRazaPerro = listOf<String>(
        "Bichón",
        "Pekines"
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
        _raza.set("")
        _tipo.postValue(tipo)
        pintarItemsRaza(tipo)
    }
    fun onRazaChange(raza: String) {
        _raza.set(raza)
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

    fun onFinalizarPress(navController: NavController) {
        /*viewModelScope.launch {
            val nombre = _nombre.value
            val tipo = _tipo.value
            val numChip = _numChip.value
            val colorAsig = _colorAsig.value

            if (nombre.isNullOrBlank() || tipo.isNullOrBlank() ||
                numChip.isNullOrBlank() || colorAsig.isNullOrBlank()) {
                return@launch
            }else{
                navController.navigate(route = "home")

            }
        }*/
        navController.navigate(route = "home") //Para que no rompa
    }

}