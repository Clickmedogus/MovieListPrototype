package com.example.movielistprototype

import com.example.movielistprototype.data.model.request.ApiInterface
import com.example.movielistprototype.data.model.response.PeopleResponse
import com.example.movielistprototype.utils.Resource
import com.example.movielistprototype.utils.Resource.Success
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class PeopleRespository @Inject constructor(
    private val apiInterface: ApiInterface
) {

    suspend fun getUserResponse(): Resource<List<PeopleResponse>> {
        val response = try {
            apiInterface.getPeopleData()
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }

        return Success(response)
    }
}