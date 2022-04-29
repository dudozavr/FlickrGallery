package com.dudnyk.framework.flickrgallery.common

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T?) : Resource<T>(data)
    class Success<T>(data: T) : Resource<T>(data)
    class NetworkError<T>(data: T? = null, message: String) : Resource<T>(data, message)
    class Error<T>(data: T? = null, message: String) : Resource<T>(data, message)
}