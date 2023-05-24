package com.example.movielistprototype.coilClass

import android.content.Context
import android.graphics.drawable.Drawable
import coil.ImageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.movielistprototype.R
import com.example.movielistprototype.interfaces.ImageLoaderInterface
import javax.inject.Inject

class CoilImageLoader @Inject constructor(private val context: Context) : ImageLoaderInterface {
    override fun loadImage(url: String, callback: (drawable: Drawable) -> Unit) {

        val request = ImageRequest.Builder(context)
            .data(url)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .error(R.drawable.error_image)
            .target { drawable ->
                callback(drawable)
            }
            .build()
        val disposable = ImageLoader(context)
        disposable.enqueue(request)


    }
}

