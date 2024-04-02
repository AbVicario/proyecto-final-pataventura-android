package com.example.pataventura.ui.screens.registoMascota.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pataventura.R
import com.example.pataventura.ui.composables.CustomOutlinedTextPerfilMascota
import com.example.pataventura.ui.composables.CustomOutlinedTextPerfilMascotaDesplegable
import com.example.pataventura.ui.composables.CustomOutlinedTextPerfilMascotaDesplegableColor
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.composables.IconButtonImage
import com.example.pataventura.ui.composables.IconButtonImageMascota
import com.example.pataventura.ui.composables.LoginButton
import com.example.pataventura.ui.screens.registoMascota.RegistroMascotaViewModel
import com.example.pataventura.ui.screens.registro.RegistroViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde

@Composable
fun BodyRegistroMascota(registroMascotaViewModel: RegistroMascotaViewModel,
                        navController:NavController) {
    var itemsTipo = listOf<String>(
        "Perro",
        "Gato"
    )
    var itemsColores = listOf<String>(
        "Rojo",
        "Azul",
        "Verde",
        "Negro"
    )
    var itemsEdad = listOf<String>(
        "Cachorro",
        "Adulto",
        "Senior"
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo_registro_mascotas),
            contentDescription = "Silueta gato", Modifier.fillMaxSize()
        )
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding( top = 15.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally)
        {

            CustomText(
                text = "Introduce los datos de tu mascota",
                color = Verde, fontSize = 20.sp,
                fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily
            )

            Row (
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically)
            {

                IconButtonImageMascota()
                Spacer(modifier = Modifier.width(10.dp))
                CustomOutlinedTextPerfilMascota(true,placeholder = "Nombre:",
                    keyboardType = KeyboardType.Text, false)
                {
                    registroMascotaViewModel.onNombreChange(
                        it
                    )
                }

            }
            Row (
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically)
            {
                Box(Modifier.fillMaxWidth(0.5f).padding(horizontal = 5.dp)){
                    CustomOutlinedTextPerfilMascotaDesplegable(
                        items = itemsTipo,
                        placeholder = "Tipo:",
                        keyboardType = KeyboardType.Text,
                        false,
                        onTextFieldChange = {
                        },
                        onItemSelected = {
                            registroMascotaViewModel.onTipoChange(it)


                        }
                    )
                }
                Box(Modifier.fillMaxWidth().padding(horizontal = 5.dp))
                {
                    CustomOutlinedTextPerfilMascotaDesplegable(
                        items= registroMascotaViewModel.listaRaza,
                        placeholder = "Raza:",
                        keyboardType = KeyboardType.Text,
                        false,
                        onTextFieldChange = {
                            registroMascotaViewModel.onRazaChange(it)
                        },
                        onItemSelected = {

                        }
                    )
                }
            }

            Row (
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically)
            {
                Box(Modifier.fillMaxWidth(0.5f).padding(horizontal = 5.dp))
                {
                    CustomOutlinedTextPerfilMascotaDesplegable(
                        items= itemsEdad,
                        placeholder = "Edad:",
                        keyboardType = KeyboardType.Text,
                        true,
                        onTextFieldChange = {
                            registroMascotaViewModel.onRazaChange(it)
                        },
                        onItemSelected = {

                        }
                    )
                }
                Box(Modifier.fillMaxWidth().padding(horizontal = 5.dp))
                {
                    CustomOutlinedTextPerfilMascota(true,placeholder = "Peso:" ,
                        keyboardType = KeyboardType.Number, true)
                    {
                        registroMascotaViewModel.onPesoChange(
                            it
                        )
                    }
                }
            }

            CustomOutlinedTextPerfilMascota(false,placeholder = "Descripción:" ,
                keyboardType = KeyboardType.Text, true)
            {
                registroMascotaViewModel.onDescripcionChange(
                    it
                )
            }

            Row (
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically)
            {
                Box(Modifier.fillMaxWidth(0.5f).padding(horizontal = 5.dp))
                {
                    CustomOutlinedTextPerfilMascota(true,placeholder = "Nº Chip:" ,
                        keyboardType = KeyboardType.Number, false)
                    {
                        registroMascotaViewModel.onPesoChange(
                            it
                        )
                    }
                }
                Box(Modifier.fillMaxWidth().padding(horizontal = 5.dp))
                {
                    CustomOutlinedTextPerfilMascotaDesplegableColor(
                        items = itemsColores,
                        placeholder = "Color asignado:", false,
                        onTextFieldChange = {
                            registroMascotaViewModel.oncolorAsigChange(it)
                        },
                        onItemSelected = {


                        }
                    )
                }
            }

            Box(
                Modifier
                    .fillMaxWidth(0.7f)
                    .height(40.dp)
                )
            {
                LoginButton(text = "Finalizar",
                    onClick = {registroMascotaViewModel.onFinalizarPress(navController)} )
            }
        }
    }
}


