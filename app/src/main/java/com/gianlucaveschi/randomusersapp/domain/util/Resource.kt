package com.gianlucaveschi.randomusersapp.domain.util

sealed interface Resource<T> {
    class Success<T>(data: T) : Resource<T>
    class Error<T>(message: String) : Resource<T>
}

// Improved version could be:
//sealed interface Resource<T> {
//    data class Success<T>(val data: T) : Resource<T>
//    data class Error<T>(val t: Throwable) : Resource<T>
//}