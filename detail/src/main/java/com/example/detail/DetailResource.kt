package com.example.detail

sealed class DetailResource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): DetailResource<T>(data)
    class Error<T>(message: String, data: T? = null): DetailResource<T>(data, message)
    class Loading<T>(data: T? = null): DetailResource<T>(data)
}