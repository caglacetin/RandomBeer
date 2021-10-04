package com.caglacetin.randombeer.main

import com.caglacetin.randombeer.util.DummyBeerItem.createDummyBeerItem
import com.caglacetin.randombeer.common.Resource
import com.caglacetin.randombeer.ui.MainViewState
import com.google.common.truth.Truth
import org.junit.Test

class MainViewStateTest {

  @Test
  fun `should return loading true when status is loading`() {
    // Given
    val givenViewState = MainViewState(status = Resource.Loading)
    // When
    val actualResult = givenViewState.isLoading()
    // Then
    Truth.assertThat(actualResult).isTrue()
  }

  @Test
  fun `should not return loading false when status is error`() {
    // Given
    val givenViewState = MainViewState(status = Resource.DataError(Exception()))
    // When
    val actualResult = givenViewState.isLoading()
    // Then
    Truth.assertThat(actualResult).isFalse()
  }

  @Test
  fun `should not return loading false when status is success`() {
    // Given
    val givenViewState = MainViewState(status = Resource.Success(createDummyBeerItem()))
    // When
    val actualResult = givenViewState.isLoading()
    // Then
    Truth.assertThat(actualResult).isFalse()
  }

  @Test
  fun `should return correct error message when status is error`() {
    // Given
    val givenViewState = MainViewState(
      status = Resource.DataError(Exception("500 Internal Server Error")))
    // When
    val actualResult = (givenViewState.getErrorMessage() as Throwable).message
    // Then
    Truth.assertThat(actualResult).isEqualTo("500 Internal Server Error")
  }

  @Test
  fun `should return true for error message visibility when status is error`() {
    // Given
    val givenViewState = MainViewState(
      status = Resource.DataError(Exception("500 Internal Server Error")))
    // When
    val actualResult = givenViewState.shouldShowErrorMessage()
    // Then
    Truth.assertThat(actualResult).isTrue()
  }
}
