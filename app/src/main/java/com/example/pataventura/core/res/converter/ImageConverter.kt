package com.example.pataventura.core.res.converter
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageConverter {
    suspend fun toImageBitmap(byteArray: ByteArray): ImageBitmap {
        return withContext(Dispatchers.Default) {
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            bitmap.asImageBitmap()
        }
    }
}