package com.example.movielistprototype.Module

import android.app.Application
import android.content.Context
import com.example.movielistprototype.CoilClass.CoilImageLoader
import com.example.movielistprototype.interfaces.ImageLoaderInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object AppModule {
    @Provides
    @ActivityScoped
    fun provideImageLoader(application: Application): ImageLoaderInterface {
        val context: Context = application.applicationContext
        return CoilImageLoader(context)
    }

}
