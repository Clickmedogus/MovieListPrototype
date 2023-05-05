package com.example.movielistprototype

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movielistprototype.data.model.response.PeopleResponse
import com.example.movielistprototype.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val peopleRespository: PeopleRespository): ViewModel() {

    var isLoading = mutableStateOf(false)
    private var _getUserData: MutableLiveData<List<PeopleResponse>> = MutableLiveData<List<PeopleResponse>>()
    var getUserData: LiveData<List<PeopleResponse>> = _getUserData

    suspend fun getUserData(): Resource<List<PeopleResponse>> {
        val result = peopleRespository.getUserResponse()
        if (result is Resource.Success) {
            isLoading.value = true
            _getUserData.value = result.data!!
        }

        return result
    }
}