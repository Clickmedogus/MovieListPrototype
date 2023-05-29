package com.example.detail.DetailViewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.detail.DetailResource
import com.example.detail.detailData.model.DetailPeople
import com.example.detail.detailData.repository.PeopleRespository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewmodel @Inject constructor(
    private val peopleRepository: PeopleRespository
) : ViewModel() {

    private val _userData: MutableStateFlow<DetailResource<List<DetailPeople>>> =
        MutableStateFlow(DetailResource.Loading(emptyList()))
    val userData: StateFlow<DetailResource<List<DetailPeople>>> = _userData

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
                if (result is DetailResource.Success<*>) {
                    _userData.value = result
                } else if (result is DetailResource.Error<*>) {
                    _userData.value = DetailResource.Error(result.message.orEmpty())
                }
            } catch (e: Exception) {
                _userData.value = DetailResource.Error(e.message.orEmpty())
            } finally {
                _isLoading.value = false // İşlem tamamlandığında false olarak ayarlanır.
            }
        }
    }
}
