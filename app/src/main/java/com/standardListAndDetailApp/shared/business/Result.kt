package com.standardListAndDetailApp.shared.business

sealed class Result<out T> {
    data class OnFailure(val exception: Exception) : Result<Nothing>()
    data class OnSuccess<T>(val data: T) : Result<T>()

}