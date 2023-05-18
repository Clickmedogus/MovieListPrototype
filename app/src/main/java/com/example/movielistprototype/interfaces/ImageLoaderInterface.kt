package com.example.movielistprototype.interfaces

import android.graphics.drawable.Drawable

interface ImageLoaderInterface {
    fun loadImage(url: String, callback: (drawable: Drawable) -> Unit)
}