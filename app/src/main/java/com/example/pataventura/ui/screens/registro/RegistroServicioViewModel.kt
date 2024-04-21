package com.example.pataventura.ui.screens.registro

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.pataventura.core.navigations.Destinations
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class RegistroServicioViewModel @Inject constructor(
) : ViewModel(){

    private val _servicio = MutableLiveData<String>()
    val servicio: LiveData<String> = _servicio
    private val _descripcion = MutableLiveData<String>()
    val descripcion: LiveData<String> = _descripcion
    private val _precio = MutableLiveData<String>()
    val precio: LiveData<String> = _precio

    private val _isServicioSeleccionado = MutableLiveData<Boolean>()
    val isServicioSeleccionado: LiveData<Boolean> = _isServicioSeleccionado
    private val _isRango = MutableLiveData<Boolean>()
    val isRango: LiveData<Boolean> = _isRango
    private val _isDescripcion = MutableLiveData<Boolean>()
    val isDescripcion: LiveData<Boolean> = _isDescripcion
    private val _isPrecio = MutableLiveData<Boolean>()
    val isPrecio: LiveData<Boolean> = _isPrecio
    private val _isPrecioValido = MutableLiveData<Boolean>()
    val isPrecioValido: LiveData<Boolean> = _isPrecioValido

    private val _rango = MutableLiveData<String>("")
    val rango: LiveData<String> = _rango

    private val _listaRango: ObservableList<String> = ObservableArrayList<String>().apply {
        add("")}
    val listaRango: ObservableList<String> = _listaRango

    private var listaDistanciaPaseo = listOf("500m", "1000m", "1500m", "2000m", "2500m", "3000m",
        "3500m")
    private var listaDistanciaGuarderia = listOf( "5Km", "7,5km", "10Km", "12.5km", "15km",
        "17.5km", "20Km")

    private fun rangoAccion(servicio: String){
        val itemsToAdd : MutableList<String> =
            when (servicio){
                "Paseo" ->  listaDistanciaPaseo.toMutableList()
                "Guardería" ->  listaDistanciaGuarderia.toMutableList()
                else -> listOf("").toMutableList()
            }
        _listaRango.clear()
        _listaRango.addAll(itemsToAdd)
    }
    fun onChangeDescripcion(descripcion:String){
        _descripcion.postValue(descripcion)
    }
    fun onChangeRango(rango:String){
        _rango.postValue(rango)
    }
    fun onChangeServicio(servicio:String){

        _servicio.postValue(servicio)
        rangoAccion(servicio)
        onChangeRango("")
    }
    fun onChangePrecio(precio:String){
        _precio.postValue(precio)
    }
    fun onPressRegistroServicio(navController:NavController){
        val regex = Regex("^\\d*([,.]\\d+)?\$")
        var descripcion = _descripcion.value
        var precio = _precio.value
        var rango = _rango.value
        var servicio = _servicio.value
        _isDescripcion.postValue(false)
        _isPrecio.postValue(false)
        _isRango.postValue(false)
        _isServicioSeleccionado.postValue(false)
        _isPrecioValido.postValue(false)
        
        if(descripcion.isNullOrBlank()){
            _isDescripcion.postValue(true)
        }
        if(rango.isNullOrBlank()){
            _isRango.postValue(true)
        }
        if(servicio.isNullOrBlank()){
            _isServicioSeleccionado.postValue(true)
        }
        if(precio.isNullOrBlank()){
            _isPrecio.postValue(true)
        }else if(!regex.matches(precio)){
            _isPrecioValido.postValue(true)
        }
        if(_isDescripcion.value==false && _isPrecio.value==false && _isPrecioValido.value==false
            && _isRango.value==false && _isServicioSeleccionado.value == false ){
            navController.navigate(route = Destinations.Home.route)
        }
    }

}