package com.example.pataventura.ui.screens.perfilTutor.composables

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
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
import com.example.pataventura.ui.composables.CustomOutlinedTextField
import com.example.pataventura.ui.composables.CustomText
import com.example.pataventura.ui.screens.perfilTutor.PerfilTutorViewModel
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Verde

@Composable
fun BodyPerfilTutor(perfilTutorViewModel: PerfilTutorViewModel,
                       navController: NavController
){
    Box(){
        Image(painter = painterResource(id = R.drawable.fondo_perro_gato_perro),
            contentDescription = "Fondo",
            Modifier.fillMaxSize())
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                CulumnNombre()
                Spacer(modifier = Modifier.size(10.dp))
                ColumnCaracteristicas()
            }
    }
}
@Composable
fun CulumnNombre() {
    Column (horizontalAlignment = Alignment.Start){
        CustomText(text = "Espe Cruz", color = Verde, fontSize = 30.sp,
            fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily)
        CustomText(text = "Tutor", color = Color.Black, fontSize = 14.sp,
            fontWeight = FontWeight.Bold, fontFamily = CustomFontFamily)
    }
}

@Composable
fun ColumnCaracteristicas() {
    Column (verticalArrangement = Arrangement.SpaceBetween){

        CustomOutlinedTextField(
            onValueChange = {},
            Modifier
                .fillMaxWidth()
                .height(100.dp),
            enabled = true,
            readOnly = false,
            placeholder = "Alias",
            supportingText = {},
            leadingIcon = { Icon(Icons.Default.Person, null) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.size(14.dp))
        CustomOutlinedTextField(
            onValueChange = {},
            Modifier
                .fillMaxWidth()
                .height(100.dp),
            enabled = true,
            readOnly = false,
            placeholder = "Teléfono",
            supportingText = {},
            leadingIcon = { Icon(Icons.Default.Phone, null) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Spacer(modifier = Modifier.size(14.dp))
        CustomOutlinedTextField(
            onValueChange = {},
            Modifier
                .fillMaxWidth()
                .height(100.dp),
            enabled = true,
            readOnly = false,
            placeholder = "Email",
            leadingIcon ={ Icon(Icons.Default.Email, null) },
            supportingText = {},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true
        )
        Spacer(modifier = Modifier.size(14.dp))
        CustomOutlinedTextField(
            onValueChange = {},
            Modifier
                .fillMaxWidth()
                .height(100.dp),
            enabled = true,
            readOnly = false,
            placeholder = "Dirección",
            leadingIcon ={ Icon(Icons.Default.Home, null) },
            supportingText = {},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true
        )
    }
}
