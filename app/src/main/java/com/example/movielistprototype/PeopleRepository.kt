package com.example.movielistprototype

import com.example.movielistprototype.data.ApiInterface
import com.example.movielistprototype.data.model.People
import com.example.movielistprototype.utils.Resource
import com.example.movielistprototype.utils.Resource.Success
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PeopleRespository @Inject constructor(
    private val apiInterface: ApiInterface
) {

    suspend fun getUserResponse(): Resource<List<People>> {
        val response = try {
            apiInterface.getPeopleData().results
        } catch (e: Exception) {
            println("dadsad"+e.localizedMessage)
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }

        return Success(response)
    }
}