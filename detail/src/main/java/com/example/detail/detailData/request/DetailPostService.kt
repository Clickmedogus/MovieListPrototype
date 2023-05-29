package com.example.detail.detailData.request

import com.example.detail.detailData.response.DetailPeopleResponse
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ApiInterface {
    @GET("people")
    suspend fun getPeopleDtoData(): DetailPeopleResponse
}