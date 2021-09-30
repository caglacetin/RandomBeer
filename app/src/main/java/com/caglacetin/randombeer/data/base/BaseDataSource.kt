package com.caglacetin.randombeer.data.base

import com.caglacetin.randombeer.common.Resource
import com.caglacetin.randombeer.common.Resource.DataError
import com.caglacetin.randombeer.common.Resource.Success
import retrofit2.Response
import java.io.IOException

@Suppress("UNCHECKED_CAST")
open class BaseDataSource {

  suspend fun <T : Any> processCall(responseCall: suspend () -> Response<T>): Resource<T> {
    return try {
      val response = responseCall.invoke()
      if (response.isSuccessful) {
        Success(response.body() ?: Any() as T)
      } else {
        DataError(
          RuntimeException(
            response.errorBody()?.toString() ?: response.message()
          )
        )
      }
    } catch (error: IOException) {
      DataError(error)
    }
  }
}
