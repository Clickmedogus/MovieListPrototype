package com.example.movielistprototype.data

import com.example.movielistprototype.data.model.People
import com.example.movielistprototype.data.response.PeopleResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ApiInterface {
    @GET("people")
    suspend fun getPeopleData(): PeopleResponse

    @GET("people/{id}")
    suspend fun getPeopleData(@Query("id") id: Int): List<People>
}