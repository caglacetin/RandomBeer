package com.caglacetin.randombeer.common

// references :
// https://developer.android.com/jetpack/docs/guide#addendum

sealed class Resource<out T> {
  class Success<T>(val data: T) : Resource<T>()
  class DataError(val exception: Throwable) : Resource<Nothing>()
  object Loading : Resource<Nothing>()
}

inline fun <T, R> Resource<T>.map(transform: (T) -> R): Resource<R> {
  return when (this) {
    is Resource.Success -> Resource.Success(transform(data))
    is Resource.DataError -> Resource.DataError(exception)
    is Resource.Loading -> Resource.Loading
  }
}
