package com.example.movielistprototype.data.model.request

import com.example.movielistprototype.data.model.response.PeopleResponse

import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ApiInterface {
    @GET("people")
    suspend fun getPeopleData(): List<PeopleResponse>
}