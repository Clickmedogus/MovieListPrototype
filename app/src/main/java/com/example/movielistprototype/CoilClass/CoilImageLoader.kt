package com.example.movielistprototype.CoilClass

import android.content.Context
import android.graphics.drawable.Drawable
import coil.ImageLoader
import coil.request.ImageRequest
import com.example.movielistprototype.interfaces.ImageLoaderInterface
import javax.inject.Inject

class CoilImageLoader @Inject constructor(private val context: Context) : ImageLoaderInterface {
      override fun loadImage(url: String, callback: (drawable: Drawable) -> Unit) {
          val request = ImageRequest.Builder(context)
              .data(url)
              .target { drawable ->
                  callback(drawable)
              }
              .build()

        val imageLoader = ImageLoader(context)
        imageLoader.enqueue(request)
    }
}