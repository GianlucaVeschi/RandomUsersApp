package com.gianlucaveschi.randomusersapp.domain.util

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
}

//sealed interface Resource<T> {
//    data class Success<T>(val data: T) : Resource<T>
//    data class Error<T>(val t: Throwable) : Resource<T>
//}