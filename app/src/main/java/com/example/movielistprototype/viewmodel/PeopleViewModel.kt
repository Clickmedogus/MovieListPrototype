package com.example.movielistprototype.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielistprototype.data.model.People
import com.example.movielistprototype.repository.PeopleRespository
import com.example.movielistprototype.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val peopleRespository: PeopleRespository
): ViewModel() {

    var isLoading = mutableStateOf(false)
    private var _getUserData: MutableStateFlow<List<People>> = MutableStateFlow(emptyList())
    var getUserData: MutableStateFlow<List<People>> = _getUserData


    fun getUserData(): StateFlow<Resource<List<People>>> {
        return object : StateFlow<Resource<List<People>>> {
            override val replayCache: List<Resource<List<People>>>
                get() = TODO("Not yet implemented")

            override val value: Resource<List<People>> = Resource.Loading(emptyList())

            override suspend fun collect(collector: FlowCollector<Resource<List<People>>>)
            = this@PeopleViewModel.getUserData().collect(collector)
        }
    }

    init {
        fetchUserData()
    }

    private fun fetchUserData() {
        viewModelScope.launch {

            flow {
                val result = peopleRespository.getUserResponse()
                if (result is Resource.Success) {
                    isLoading.value = true
                    _getUserData.value = result.data!!
                }

                emit(result)
            }.collect {
                getUserData.value = it.data!!
            }
        }


    }
}