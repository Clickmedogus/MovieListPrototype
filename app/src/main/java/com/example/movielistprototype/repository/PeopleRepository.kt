package com.example.movielistprototype.repository

import com.example.movielistprototype.data.model.People
import com.example.movielistprototype.data.request.ApiInterface
import com.example.movielistprototype.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PeopleRespository @Inject constructor(
    private val apiInterface: ApiInterface
) {

    suspend fun getUserResponse(): Resource<List<People>> {
        return try {
            val response = apiInterface.getPeopleData().results
            Resource.Success(response.toList())
        } catch (e: Exception) {
            Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }
    }
}