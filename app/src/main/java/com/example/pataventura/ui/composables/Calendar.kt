package com.example.pataventura.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.util.Locale

@Composable
fun MyCalendar() {
    val currentMonth = LocalDate.now().month
    val currentYear = LocalDate.now().year
    val daysInMonth = currentMonth.length(true)
    val firstDayOfMonth = LocalDate.of(currentYear, currentMonth, 1).dayOfWeek.value

    val months = arrayOf("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
        "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre")
    val header = months[currentMonth.value - 1] + " " + currentYear.toString()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = header,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyVerticalGrid(columns = GridCells.Fixed(7)) {
            items(daysInMonth + firstDayOfMonth - 1) { index ->
                val day = if (index >= firstDayOfMonth - 1) {
                    (index - (firstDayOfMonth - 1)).toString()
                } else {
                    ""
                }
                DayItem(day)
            }
        }
    }
}

@Composable
fun DayItem(day: String) {
    Text(
        text = day,
        color = Color.Black,
        fontWeight = FontWeight.Normal,
        modifier = Modifier
            .padding(4.dp)
            .width(40.dp),
        textAlign = TextAlign.Center
    )
}