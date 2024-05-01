package com.example.pataventura.ui.screens.home.composables

import HandleLocationPermissionAndState
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.pataventura.R
import com.example.pataventura.ui.composables.CustomOutlinedTextFieldDes
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.screens.home.HomeViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun BodyHome(currentPosition: LatLng?, homeViewModel: HomeViewModel){
    var listaServicios = listOf("Guardería", "Paseo")

    val servicio : String by homeViewModel.servicio.observeAsState("")
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

        CustomOutlinedTextFieldDes(
            text = servicio,
            items = listaServicios,
            onValueChange = {homeViewModel.onRolChange(it)},
            modifier = Modifier.fillMaxWidth().height(80.dp),
            enabled = true,
            readOnly = false,
            placeholder = "Tipo de servicio",
            supportingText = {},
            singleLine = true
        )


        MyBoxMap(currentPosition)
        HandleLocationPermissionAndState(homeViewModel)
        
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
fun MyBoxMap(currentPosition: LatLng?/*, cameraState: CameraPositionState?*/) {
    var marker = LatLng(40.416775, -3.703790)
    var cameraStateAux = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(marker, 10f)
    }

    if(currentPosition!= null ){
        marker = LatLng(currentPosition.latitude, currentPosition.longitude)
        cameraStateAux=rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(currentPosition, 12f)
        }
    }

  GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 40.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxHeight(0.9f),
        cameraPositionState = cameraStateAux,
        properties = MapProperties(
            isMyLocationEnabled = true,
            mapType = MapType.NORMAL,
            isTrafficEnabled = false
        )
    ){
      Marker(
          state = MarkerState(position = marker),
          title = "My Position Yuju!",
          snippet = "Lo he logrado. Chachi piruli",
          draggable = true
      )
    }

}


@Composable
fun RationaleAlert(onDismiss: () -> Unit, onConfirm: () -> Unit) {

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties()
    ) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "We need location permissions to use this app",
                )
                Spacer(modifier = Modifier.height(24.dp))
                TextButton(
                    onClick = {
                        onConfirm()
                        onDismiss()
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("OK")
                }
            }
        }
    }
}



