package com.example.pataventura.ui.screens.servicio.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pataventura.R
import com.example.pataventura.di.TiposServicio
import com.example.pataventura.domain.model.Servicio
import com.example.pataventura.ui.composables.CampoObligatorioText
import com.example.pataventura.ui.composables.CustomOutlinedTextFieldUpdate
import com.example.pataventura.ui.composables.CustomOutlinedTextFieldUpdateDes
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.composables.LoginButton
import com.example.pataventura.ui.screens.servicio.ServicioViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde

@Composable
fun BodyServicio(
                 servicioViewModel: ServicioViewModel,
                 navController: NavController
) {
    val listaServicios: List<Servicio> by servicioViewModel.listaServicios.observeAsState(
        initial = emptyList()
    )

    Box{
        Image(
            painter = painterResource(id = R.drawable.fondo_perro_gato_perro),
            contentDescription = "Fondo",
            Modifier.fillMaxSize()
        )

        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            MyRowServicio(navController, listaServicios)
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(bottom = 35.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(listaServicios.size) { index ->
                    val servicio = listaServicios[index]
                    val estado = servicioViewModel.servicioStates.observeAsState().value?.get(servicio.idOferta) ?: ServicioViewModel.ServicioState(servicio)

                    MyServicioColumn(
                        editMode = estado.editMode,
                        navController = navController,
                        servicio = servicio,
                        radio = estado.radio,
                        precio = estado.precio,
                        descripcion = estado.descripcion,
                        servicioViewModel = servicioViewModel,
                        isPrecio = estado.precio.isNotEmpty(),
                        isDescripcion = estado.descripcion.isNotEmpty(),
                        isRadio = estado.radio.isNotEmpty()
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}

@Composable
fun MyServicioColumn(
    editMode: Boolean,
    navController: NavController,
    servicio: Servicio,
    radio: String,
    precio: String,
    descripcion: String,
    servicioViewModel: ServicioViewModel,
    isPrecio: Boolean? = null,
    isDescripcion: Boolean? = null,
    isRadio: Boolean? = null,
) {

    val listaRangosPaseo: List<String> = listOf(
        "500m", "1000m", "1500m", "2000m", "2500m", "3000m",
        "3500m"
    )
    val listaRangosGuarderia: List<String> = listOf(
        "5Km", "7.5km", "10Km", "12.5km", "15km",
        "17.5km", "20Km"
    )

    Column(
        Modifier
            .fillMaxWidth()
            .border(3.dp, Verde, shape = RoundedCornerShape(20.dp))
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            CustomText(
                text = servicio.tipo,
                color = Verde,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = CustomFontFamily,
            )
            Row (){
                if (!editMode) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Verde, RoundedCornerShape(100f))
                            .clickable {
                                servicioViewModel.onValueChangeEditMode(/*true*/servicio.idOferta,
                                    true
                                )
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "Editar Servicio",
                            Modifier.size(25.dp),
                            tint = Color.White,

                            )
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Red, RoundedCornerShape(100f))
                        .clickable {
                            servicioViewModel.onDelete(servicio)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Eliminar servicio",
                        Modifier.size(25.dp),
                        tint = Color.White,

                        )
                }

            }

        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomOutlinedTextFieldUpdateDes(
                valueAux = radio,
                items = if (servicio.tipo == "Paseo") listaRangosPaseo else listaRangosGuarderia,
                onValueChange = { servicioViewModel.onValueChangeRadio(/*it*/servicio.idOferta, it) },
                Modifier
                    .fillMaxWidth(0.45f)
                    .height(100.dp),
                enabled = editMode,
                readOnly = !editMode,
                placeholder = "Radio",

                supportingText = { if (isRadio != null && !isRadio) CampoObligatorioText() },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true
            )
            CustomOutlinedTextFieldUpdate(
                valueAux = precio,
                onValueChange = { servicioViewModel.onValueChangePrecio(/*it*/servicio.idOferta, it) },
                Modifier
                    .fillMaxWidth(0.85f)
                    .height(100.dp),
                enabled = editMode,
                readOnly = !editMode,
                placeholder = "Precio",
                supportingText = { if (isPrecio != null && !isPrecio) CampoObligatorioText() },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        CustomOutlinedTextFieldUpdate(
            valueAux = descripcion,
            onValueChange = { servicioViewModel.onValueChangeDescripcion(/*it*/servicio.idOferta, it) },
            Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(horizontal = 20.dp),
            enabled = editMode,
            readOnly = !editMode,
            placeholder = "Descripción",
            supportingText = { if (isDescripcion != null && !isDescripcion) CampoObligatorioText() },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = false
        )
        Spacer(modifier = Modifier.height(20.dp))

        if (editMode) {
            LoginButton(text = "Guardar", null, null,) {
                servicioViewModel.onSave(navController, servicio)
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

}

@Composable
fun MyRowServicio(
    navController: NavController,
    listaServicios: List<Servicio>
) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CustomText(
            text = "Mis servicios",
            color = Verde,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = CustomFontFamily
        )
        if (listaServicios.size < TiposServicio.tiposServicio.value!!.size) {
            Box(
                Modifier
                    .size(40.dp)
                    .background(Verde, RoundedCornerShape(100f))
                    .clickable {
                        navController.navigate("registroServicio")
                    },
                contentAlignment = Alignment.Center
            )
            {
                Icon(
                    Icons.Default.Add, contentDescription = "Añadir servicio",
                    Modifier.size(25.dp), tint = Color.White
                )
            }
        }
    }
}
