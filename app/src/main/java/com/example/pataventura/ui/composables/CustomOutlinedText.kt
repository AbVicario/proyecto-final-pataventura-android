package com.example.pataventura.ui.composables
import android.app.DatePickerDialog
import android.graphics.drawable.Icon
import android.icu.text.ListFormatter
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.size.Size
import com.example.pataventura.ui.theme.CustomFontFamily
import com.example.pataventura.ui.theme.Tierra
import com.example.pataventura.ui.theme.Verde

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.Locale


@Composable
fun CustomOutlinedTextPass(placeholder: String, opcional: Boolean,
                           onTextFieldChange: (String) -> Unit) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isItemSelected by remember { mutableStateOf(opcional) }
    var text by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            isItemSelected = true
            onTextFieldChange(it)
        },
        textStyle = TextStyle(fontSize = 20.sp),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        label = { Text(text = placeholder, fontWeight = FontWeight.Bold, fontSize = 22.sp,
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 2.dp))},
        leadingIcon = { Icon(Icons.Default.Lock, null) },
        trailingIcon = {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    imageVector = if (isPasswordVisible) Icons.Default.Visibility
                    else Icons.Default.VisibilityOff,
                    contentDescription = "Toggle password visibility",
                )
            }
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None
        else PasswordVisualTransformation(),
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedTrailingIconColor = Tierra,
            unfocusedTrailingIconColor = Tierra,
            focusedLabelColor = Tierra,
            unfocusedLabelColor = Tierra,
            focusedBorderColor = if(isItemSelected) Tierra else Color.Red,
            unfocusedBorderColor = if(isItemSelected) Tierra else Color.Red,
            focusedLeadingIconColor = Tierra,
            unfocusedLeadingIconColor = Tierra,
            focusedPlaceholderColor = Tierra,
            unfocusedPlaceholderColor = Tierra,
            errorBorderColor = Color.Red,
        ),
    )
}


@Composable
fun CustomOutlinedTextEmail(placeholder: String, tieneError: Boolean, onTextFieldChange: (String) -> Unit) {
    var text by remember {
        mutableStateOf("")
    }


    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onTextFieldChange(it)
        },
        textStyle = TextStyle(fontSize = 20.sp),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        label = { Text(text = placeholder, fontWeight = FontWeight.Bold, fontSize = 22.sp,
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 2.dp))},
        leadingIcon = { Icon(Icons.Default.Email, null) },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedLabelColor = Tierra,
            unfocusedLabelColor = Tierra,
            focusedBorderColor = if(tieneError) Color.Red else Tierra,
            unfocusedBorderColor = if(tieneError) Color.Red else Tierra,
            focusedLeadingIconColor = Tierra,
            unfocusedLeadingIconColor = Tierra,
            focusedPlaceholderColor = Tierra,
            unfocusedPlaceholderColor = Tierra

        ),
    )
}

@Composable
fun CustomOutlinedTextPerfil(placeholder: String, opcional: Boolean,
                             onTextFieldChange: (String) -> Unit) {
    var text by remember {
        mutableStateOf("")
    }
    var isItemSelected by remember { mutableStateOf(opcional) }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            isItemSelected = true
            onTextFieldChange(it)
        },
        textStyle = TextStyle(fontSize = 20.sp),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        maxLines = 1,
        label = { Text(text = placeholder, fontWeight = FontWeight.Bold, fontSize = 22.sp,
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 2.dp))},
        leadingIcon = { Icon(Icons.Default.Person, null) },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedLabelColor = Tierra,
            unfocusedLabelColor = Tierra,
            focusedBorderColor = if(isItemSelected) Tierra else Color.Red,
            unfocusedBorderColor = if(isItemSelected) Tierra else Color.Red,
            focusedLeadingIconColor = Tierra,
            unfocusedLeadingIconColor = Tierra,
            focusedPlaceholderColor = Tierra,
            unfocusedPlaceholderColor = Tierra

        ),
    )
}
@Composable
fun CustomOutlinedTextPerfilMascota(singleLine: Boolean ,placeholder: String,
                                    keyboardType : KeyboardType,
                                    opcional : Boolean,
                                    onTextFieldChange: (String) -> Unit) {
    var text by remember {
        mutableStateOf("")
    }
    var isItemSelected by remember { mutableStateOf(opcional) }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            isItemSelected = true
            onTextFieldChange(it)
        },
        textStyle = TextStyle(fontSize = 20.sp),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = singleLine,
        maxLines = 1,
        label = { Text(text = placeholder, fontWeight = FontWeight.Bold, fontSize = 22.sp,
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 2.dp))},
        //leadingIcon = { Icon(Icons.Default.Pets, null) },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedLabelColor = Tierra,
            unfocusedLabelColor = Tierra,
            focusedBorderColor = if(isItemSelected) Tierra else Color.Red,
            unfocusedBorderColor = if(isItemSelected) Tierra else Color.Red,
            focusedLeadingIconColor = Tierra,
            unfocusedLeadingIconColor = Tierra,
            focusedPlaceholderColor = Tierra,
            unfocusedPlaceholderColor = Tierra

        ),
    )
}

@Composable
fun CustomOutlinedTextPerfilMascotaDesplegable(
    items: List<String>,
    placeholder: String,
    keyboardType: KeyboardType,
    opcional : Boolean,
    onTextFieldChange: (String) -> Unit,
    onItemSelected: (String) -> Unit
) {
    var isDesplegado by remember { mutableStateOf(false) }
    var isItemSelected by remember { mutableStateOf(opcional) }
    var text by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onTextFieldChange(it)
        },
        readOnly = true,
        textStyle = TextStyle(fontSize = 20.sp),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
        maxLines = 1,
        label = {
            Text(
                text = placeholder,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier
                    .background(Color.White)
                    .padding(horizontal = 2.dp)
            )
        },
        trailingIcon = {
            IconButton(onClick = { isDesplegado = !isDesplegado }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    tint = Verde,
                    contentDescription = "icono desplegable",
                    modifier = Modifier.fillMaxSize()
                )
            }
        },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedLabelColor = Tierra,
            unfocusedLabelColor = Tierra,
            focusedBorderColor = if(isItemSelected) Tierra else Color.Red,
            unfocusedBorderColor = if(isItemSelected) Tierra else Color.Red,
            focusedLeadingIconColor = Tierra,
            unfocusedLeadingIconColor = Tierra,
            focusedPlaceholderColor = Tierra,
            unfocusedPlaceholderColor = Tierra
        )
    )

    if (isDesplegado) {
        MenuDesplegable(
            isDesplegado = isDesplegado,
            items = items,
            onItemSelected = { item ->
                onItemSelected(item)
                isItemSelected = true
                isDesplegado = false
                text = item
            }
        )
    }
}
@Composable
fun CustomOutlinedTextPerfilMascotaDesplegableColor(
    items: List<String>,
    placeholder: String,
    opcional : Boolean,
    onTextFieldChange: (String) -> Unit,
    onItemSelected: (String) -> Unit
) {
    var isDesplegado by remember { mutableStateOf(false) }
    var isItemSelected by remember { mutableStateOf( opcional) }
    var text by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onTextFieldChange(it)

        },
        textStyle = TextStyle(fontSize = 20.sp),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        readOnly = true,
        maxLines = 1,
        label = {
            Text(
                text = placeholder,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .background(Color.White)
                    .padding(horizontal = 2.dp)
            )
        },
        trailingIcon = {
            IconButton(onClick = { isDesplegado = !isDesplegado }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    tint = Verde,
                    contentDescription = "icono desplegable",
                    modifier = Modifier.fillMaxSize()
                )
            }
        },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedLabelColor = Tierra,
            unfocusedLabelColor = Tierra,
            focusedBorderColor = if (isItemSelected) Tierra else Color.Red,
            unfocusedBorderColor = if (isItemSelected) Tierra else Color.Red,
            focusedLeadingIconColor = Tierra,
            unfocusedLeadingIconColor = Tierra,
            focusedPlaceholderColor = Tierra,
            unfocusedPlaceholderColor = Tierra
        )
    )

    if (isDesplegado) {
        MenuDesplegableColor(
            isDesplegado = isDesplegado,
            items = items,
            onItemSelected = { item ->
                onItemSelected(item)
                isDesplegado = false
                isItemSelected = true
                text = item
            }
        )
    }
}

@Composable
fun MenuDesplegable(
    isDesplegado: Boolean,
    items: List<String>,
    onItemSelected: (String) -> Unit
) {
    DropdownMenu(
        expanded = isDesplegado,
        onDismissRequest = {  },
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .background(Color.White)
            .clip(RoundedCornerShape(50.dp))
    ) {
        items.forEach { item ->
            DropdownMenuItem(
                text= { CustomText(
                    text = item,
                    color = Verde,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = CustomFontFamily
                )},
                onClick = { onItemSelected(item) })
        }
    }
}

@Composable
fun MenuDesplegableColor(
    isDesplegado: Boolean,
    items: List<String>,
    onItemSelected: (String) -> Unit
) {
    DropdownMenu(
        expanded = isDesplegado,
        onDismissRequest = {  },
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .background(Color.White)
            .clip(RoundedCornerShape(50.dp))
    ) {
        items.forEach { item ->
            DropdownMenuItem(
                text= {
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Box(
                            Modifier
                                .size(15.dp)
                                .background(color = ObtenerColor(item))
                            )
                        Spacer(modifier = Modifier.width(5.dp))
                        CustomText(
                            text = item,
                            color = Verde,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = CustomFontFamily
                        )
                    }
                    },
                onClick = { onItemSelected(item) })
        }
    }
}

fun ObtenerColor(item:String): Color {
    return when (item){
        "Azul" -> Color.Blue
        "Rojo" -> Color.Red
        "Verde" -> Color.Green
        "Negro" -> Color.Black
        else -> Color.Yellow
    }

}


@Composable
fun CustomOutlinedTextPhone(placeholder: String, opcional: Boolean,
                            onTextFieldChange: (String) -> Unit) {
    var isItemSelected by remember { mutableStateOf(opcional) }
    var text by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            isItemSelected = true
            onTextFieldChange(it)
        },
        textStyle = TextStyle(fontSize = 20.sp),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        singleLine = true,
        maxLines = 1,
        label = { Text(text = placeholder, fontWeight = FontWeight.Bold, fontSize = 22.sp,
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 2.dp))},
        leadingIcon = { Icon(Icons.Default.Phone, null) },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedLabelColor = Tierra,
            unfocusedLabelColor = Tierra,
            focusedBorderColor = if(isItemSelected) Tierra else Color.Red,
            unfocusedBorderColor = if(isItemSelected) Tierra else Color.Red,
            focusedLeadingIconColor = Tierra,
            unfocusedLeadingIconColor = Tierra,
            focusedPlaceholderColor = Tierra,
            unfocusedPlaceholderColor = Tierra

        ),
    )
}

@Composable
fun CustomOutlinedTextHome(placeholder: String, opcional: Boolean,
                           onTextFieldChange: (String) -> Unit) {
    var text by remember {
        mutableStateOf("")
    }
    var isItemSelected by remember { mutableStateOf(opcional) }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            isItemSelected = true
            onTextFieldChange(it)
        },
        textStyle = TextStyle(fontSize = 20.sp),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        maxLines = 1,
        label = { Text(text = placeholder, fontWeight = FontWeight.Bold, fontSize = 22.sp,
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 2.dp))},
        leadingIcon = { Icon(Icons.Default.Home, null) },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedLabelColor = Tierra,
            unfocusedLabelColor = Tierra,
            focusedBorderColor = if(isItemSelected) Tierra else Color.Red,
            unfocusedBorderColor = if(isItemSelected) Tierra else Color.Red,
            focusedLeadingIconColor = Tierra,
            unfocusedLeadingIconColor = Tierra,
            focusedPlaceholderColor = Tierra,
            unfocusedPlaceholderColor = Tierra

        ),
    )
}