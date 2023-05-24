package com.example.movielistprototype.view

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter
import com.example.movielistprototype.interfaces.ImageLoaderInterface

@Composable
fun LoadImage(
    url: String,
    imageLoaderInterface: ImageLoaderInterface,
    modifier: Modifier
) {
    val myDrawable: MutableState<Drawable?> = remember { mutableStateOf(null) }
    LaunchedEffect(url) {
        imageLoaderInterface.loadImage(url) { drawable ->
            myDrawable.value = drawable
        }
    }
    myDrawable.let { drawable ->
        Image(
            painter = rememberAsyncImagePainter(drawable),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = modifier
        )
    }
}
