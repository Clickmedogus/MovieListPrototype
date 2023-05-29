package com.example.detail.detailData.repository

import com.example.detail.DetailResource
import com.example.detail.detailData.mapper.toPeople
import com.example.detail.detailData.model.DetailPeople
import com.example.detail.detailData.request.ApiInterface
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PeopleRespository @Inject constructor(
    private val apiInterface: ApiInterface
) {

    suspend fun getUserResponse(): DetailResource<List<DetailPeople>> {
        return try {
            val response = apiInterface.getPeopleDtoData().results.map {
                it.toPeople()
            }
            DetailResource.Success(response.toList())
        } catch (e: Exception) {
            DetailResource.Error("An unknown error occurred: ${e.localizedMessage}")
        }
    }
}