package com.example.pataventura.ui.screens.mascotas.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pataventura.R
import com.example.pataventura.ui.composables.CustomOutlinedTextField
import com.example.pataventura.ui.composables.CustomOutlinedTextFieldBuscador
import com.example.pataventura.ui.theme.Verde

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderMascotas() {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f)
            .background(Color.Transparent)
    ) {

        HeaderBox()
        Image(
            painter = painterResource(id = R.drawable.nombre),
            contentDescription = "nombre aplicación",
            Modifier
                .width(210.dp)
                .height(100.dp)
                .align(Alignment.BottomStart)
                .padding(bottom = 15.dp, start = 30.dp)

        )

        Image(
            painter = painterResource(id = R.drawable.imagen_header_home),
            contentDescription = "Dos perros y un gato",
            Modifier
                .fillMaxSize(0.55f)
                .align(Alignment.BottomEnd)

        )

    }
}


@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HeaderBox(){
    var text by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .clip(RoundedCornerShape(bottomEnd = 40.dp))
            .background(Verde)
            .padding(top = 10.dp)
    ) {

        TextField(
            value = text,
            onValueChange = { text = it /* Do nothing for now */ },
            placeholder = { if (text.isEmpty()) {
                Text("Buscar")
            } },
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .align(Alignment.TopCenter)
                .shadow(6.dp, RoundedCornerShape(25.dp), clip = true),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(30.dp),
            leadingIcon = {
                IconButton(onClick = { /* Do nothing for now */ }) {
                    Icon(Icons.Default.Search, contentDescription = "Buscar", tint = Verde)
                }
            },
            trailingIcon = {
                if (text.isNotEmpty()) {
                    IconButton(onClick = { text = "" }) {
                        Icon(Icons.Default.Cancel, contentDescription = "Cancelar", tint = Verde)
                    }
                }
            }
        )
        Image(
            painter = painterResource(id = R.drawable.huella),
            contentDescription = "Huella", Modifier.fillMaxSize()
        )

    }
}