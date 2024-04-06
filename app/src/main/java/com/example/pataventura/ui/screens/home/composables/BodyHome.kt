package com.example.pataventura.ui.screens.home.composables

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.ImageButton
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.pataventura.R
import com.example.pataventura.ui.composables.BottomBar
import com.example.pataventura.ui.composables.CustomOutlinedTextPerfilMascotaDesplegable
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.jar.Manifest

@Composable
fun BodyHome(){
    var listaServicios = listOf("Guardería", "Paseo")
    Column (
        Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp, start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween) {
        
        CustomText(text = "Selecciona tu mascota",
            color = Verde, fontSize = 20.sp,
            fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily )
        LazyRow (){
            item {
                Spacer(modifier = Modifier.size(15.dp))
                ImageButton(painter = R.drawable.imagen_boton_gato)
                Spacer(modifier = Modifier.size(20.dp))
                ImageButton(painter = R.drawable.imagen_boton_perro)

            }

        }
        CustomText(text = "Selecciona un servicio",
            color = Verde, fontSize = 20.sp,
            fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily )

        CustomOutlinedTextPerfilMascotaDesplegable(
            items = listaServicios,
            placeholder = "Tipo de servicio",
            keyboardType = KeyboardType.Text,
            opcional = false,
            onTextFieldChange = {},
            onItemSelected = {}
        )

        MyBoxMap()
        
    }
}

@Composable
fun ImageButton(painter: Int){
    Box(
        Modifier
            .background(Color.Transparent, RoundedCornerShape(100f))
            .size(80.dp)){
        Image(painter = painterResource(painter),
            contentDescription = "Imagen mascota",
            Modifier
                .fillMaxSize()
                .clickable { })
    }
}

@Composable
@OptIn(ExperimentalCoroutinesApi::class)
fun MyBoxMap() {
    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 40.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxHeight(0.9f)
    )
}

