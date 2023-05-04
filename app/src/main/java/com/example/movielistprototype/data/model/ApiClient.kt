package com.example.movielistprototype.data.model

import retrofit2.Retrofit


object ApiClient {

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null)
            retrofit =
                Retrofit.Builder().baseUrl(Constant.baseUrl).addConverterFactory(GsonConverterFactory.create()).build()

        return retrofit as Retrofit
    }
}