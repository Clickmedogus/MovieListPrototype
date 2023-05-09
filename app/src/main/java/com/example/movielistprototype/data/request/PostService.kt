package com.example.movielistprototype.data.request

import com.example.movielistprototype.data.response.PeopleResponse
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ApiInterface {
    @GET("people")
    suspend fun getPeopleData(): PeopleResponse
}