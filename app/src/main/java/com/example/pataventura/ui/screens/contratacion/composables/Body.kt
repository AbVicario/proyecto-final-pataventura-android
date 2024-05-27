package com.example.pataventura.ui.screens.contratacion.composables
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pataventura.R
import com.example.pataventura.domain.converters.ImageConverter
import com.example.pataventura.domain.model.Cuidador
import com.example.pataventura.domain.model.Valoracion
import com.example.pataventura.ui.composables.CustomOutlinedTextContrato
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.composables.DatePickerWithDialog
import com.example.pataventura.ui.composables.MyCustomButton
import com.example.pataventura.ui.composables.MyValoracionStars
import com.example.pataventura.ui.screens.contratacion.ContratacionViewModel
import com.example.pataventura.ui.screens.home.HomeViewModel
import com.example.pataventura.ui.screens.perfil_trabajador.PerfilTrabajadorViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde
import java.time.LocalDate
@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun BodyContratacion(contratacionViewmodel: ContratacionViewModel,
                     navController: NavController,
                     homeViewModel: HomeViewModel){
    val valoraciones : List<Valoracion> by contratacionViewmodel.valoraciones.observeAsState(emptyList())
    val cuidador : Cuidador by contratacionViewmodel.cuidador.observeAsState(Cuidador())

    LaunchedEffect(key1 = Any()) {
        contratacionViewmodel.setCuidador(homeViewModel.listaCuidadores.value!!)
    }
    Box(){
        Image(painter = painterResource(id = R.drawable.fondo_perro_gato_perro),
            contentDescription = "Foondo",
            Modifier.fillMaxSize())

        Column(
            Modifier
                .fillMaxWidth()
                .height(700.dp)
                .padding(start = 15.dp, end = 15.dp, bottom = 15.dp),
            verticalArrangement = Arrangement.SpaceBetween) {

            MyCardPerfil(cuidador, valoraciones)

            MyRowFechas("Fecha inicio:", "Fecha fin:")

            CustomOutlinedTextContrato(
                singleLine = false,
                placeholder = "Notas:",
                keyboardType = KeyboardType.Text,
                opcional = true,
                altura = 260,
                onTextFieldChange = {}
            )

            MyRowPrecio()

            MyRowButtons(navController)
        }
    }



}

@Composable
fun MyRowButtons(navController: NavController) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween){
        MyCustomButton(texto = "Contartar", color = Verde){

        }
        Spacer(modifier = Modifier.width(40.dp))
        MyCustomButton(texto = "Cancelar", color = Color.Red){
            navController.navigate("home")
        }
    }
}




@Composable
fun MyRowPrecio() {
    Row(
        Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom){
        CustomText(text = "Precio total:", color = Color.Black, fontSize = 16.sp ,
            fontWeight = FontWeight.Normal, fontFamily = CustomFontFamily)
        CustomText(text = "15€" , color = Color.Black , fontSize =16.sp ,
            fontWeight = FontWeight.Normal, fontFamily = CustomFontFamily)
    }
}

@Composable
fun MyRowFechas(textoInicio: String, textoFin: String) {
    Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
        Box(modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(horizontal = 8.dp)){
            DatePickerWithDialog(
                enabled = true,
                dateFormatter = { it.toString() },
                value = LocalDate.of(2022, 10, 20),
                placeholder = {
                    Text(textoInicio)
                }) {
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)){
            DatePickerWithDialog(
                enabled = true,
                dateFormatter = { it.toString() },
                value = LocalDate.of(2022, 10, 20),
                placeholder = {
                    Text(textoFin)
                }) {
            }
        }


    }

}


fun valoracionMedia(valoraciones: List<Valoracion>?): Double {
    var resultado = 0.0
    if (valoraciones != null) {
        for (valoracion in valoraciones) {
            resultado += valoracion.puntuacion
        }
        resultado /= valoraciones.size
    }

    return resultado
}

@Composable
fun MyCardPerfil(cuidador: Cuidador,
                 valoraciones: List<Valoracion>) {
    val media = valoracionMedia(valoraciones)

    Card (modifier = Modifier.padding(10.dp),
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.elevatedCardColors(),
        elevation = CardDefaults.cardElevation(),
        border = BorderStroke(2.dp, Verde)
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),){
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                Box ( modifier = Modifier
                    .minimumInteractiveComponentSize()
                    .size(85.dp)
                    .clip(RoundedCornerShape(100f))
                    .background(Verde.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(bitmap = ImageConverter.byteArrayToImageBitmap(cuidador!!.imagen),
                        contentDescription = "Imagen cuidador",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds)
                }
            }
            Spacer(modifier = Modifier.size(15.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                CustomText(text = cuidador!!.alias, color = Color.Black, fontSize = 30.sp,
                    fontWeight = FontWeight.Bold , fontFamily = CustomFontFamily)
                Spacer(modifier = Modifier.size(5.dp))
                Row (Modifier.padding(start=10.dp)){
                    MyValoracionStars(valoracion = media, sizeStars = 40)
                }
            }
        }

    }

}
