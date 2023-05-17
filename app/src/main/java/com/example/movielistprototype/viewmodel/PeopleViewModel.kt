package com.example.movielistprototype.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielistprototype.data.model.People
import com.example.movielistprototype.repository.PeopleRespository
import com.example.movielistprototype.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val peopleRepository: PeopleRespository
) : ViewModel() {

    private val _userData: MutableStateFlow<Resource<List<People>>> =
        MutableStateFlow(Resource.Loading(emptyList()))
    val userData: StateFlow<Resource<List<People>>> = _userData

    private val _isLoading = mutableStateOf(false)
    val isLoading get() = _isLoading.value

    init {
        fetchUserData()
    }

    fun fetchUserData() {
        viewModelScope.launch {
            _isLoading.value = true // İşlem başladığında true olarak ayarlanır.
            try {
                val result = peopleRepository.getUserResponse()
                if (result is Resource.Success<*>) {
                    _userData.value = result
                } else if (result is Resource.Error<*>) {
                    _userData.value = Resource.Error(result.message.orEmpty())
                }
            } catch (e: Exception) {
                _userData.value = Resource.Error(e.message.orEmpty())
            } finally {
                _isLoading.value = false // İşlem tamamlandığında false olarak ayarlanır.
            }
        }
    }
}
