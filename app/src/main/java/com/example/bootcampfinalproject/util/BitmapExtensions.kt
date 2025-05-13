package com.example.bootcampfinalproject.util

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.palette.graphics.Palette
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun Bitmap.extractDominantAndMutedColors(onColorsReady: (Pair<Color, Color>) -> Unit) {
    CoroutineScope(Dispatchers.Default).launch {
        val palette = Palette.from(this@extractDominantAndMutedColors).generate()
        val dominantColor = palette.getDominantColor(Color.Gray.toArgb())
        val mutedColor = palette.getMutedColor(Color.Gray.toArgb())
        withContext(Dispatchers.Main) {
            onColorsReady(Pair(Color(dominantColor), Color(mutedColor)))
        }
    }
}