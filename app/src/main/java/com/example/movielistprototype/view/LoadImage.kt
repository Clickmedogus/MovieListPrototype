package com.example.movielistprototype.view

import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import coil.compose.rememberAsyncImagePainter
import com.example.movielistprototype.interfaces.ImageLoaderInterface

@Composable
fun LoadImage(
    url: String,
    modifier: Modifier,
    imageLoaderInterface: ImageLoaderInterface,
    onImageLoaded: (Painter) -> Unit
) {
    var myDrawable : Drawable? = null

    LaunchedEffect(url) {
        imageLoaderInterface.loadImage(url) { drawable ->
            myDrawable = drawable
        }
    }
    val painter: Painter = rememberAsyncImagePainter(myDrawable)
    onImageLoaded(painter)
}