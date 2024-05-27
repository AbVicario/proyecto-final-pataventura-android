package com.example.pataventura.domain.converters


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import java.io.ByteArrayOutputStream

class ImageConverter {
    companion object {
        fun imageBitmapToByteArray(imageBitmap: ImageBitmap): ByteArray {
            val stream = ByteArrayOutputStream()
            val bitmap = imageBitmap.asAndroidBitmap()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            return stream.toByteArray()
        }

        fun byteArrayToImageBitmap(byteArray: ByteArray): ImageBitmap {
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            Log.d("ImageConverter", "Bitmap size: ${byteArray.toString()}")
            return bitmap?.asImageBitmap() ?: ImageBitmap(1, 1)
        }
    }
}
