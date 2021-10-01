package com.caglacetin.randombeer.ui

import com.caglacetin.randombeer.common.Resource

class MainViewState(
  private val status: Resource<Any>
){
  fun isLoading() = status is Resource.Loading
  fun getErrorMessage() = if (status is Resource.DataError) status.exception else ""
  fun shouldShowErrorMessage() = status is Resource.DataError

}
