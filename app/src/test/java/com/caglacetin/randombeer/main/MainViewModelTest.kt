package com.caglacetin.randombeer.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.caglacetin.randombeer.common.Resource
import com.caglacetin.randombeer.domain.FetchRandomBeer
import com.caglacetin.randombeer.ui.BeerItem
import com.caglacetin.randombeer.ui.MainViewModel
import com.caglacetin.randombeer.util.CoroutineRule
import com.caglacetin.randombeer.util.DummyBeerItem.createDummyBeerItem
import com.caglacetin.randombeer.util.InstantExecutorExtension
import kotlinx.coroutines.flow.flow
import io.mockk.MockKAnnotations
import org.junit.Assert.assertEquals
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.lang.Exception

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class MainViewModelTest {

  @MockK
  lateinit var fetchRandomBeer: FetchRandomBeer

  // Set the main coroutines dispatcher for unit testing.
  @ExperimentalCoroutinesApi
  @get:Rule
  var mainCoroutineRule = CoroutineRule()

  // Executes each task synchronously using Architecture Components.
  @get:Rule
  var instantExecutorRule = InstantTaskExecutorRule()

  private lateinit var mainViewModel: MainViewModel

  @Before
  fun setUp() {
    MockKAnnotations.init(this)
    mainViewModel = MainViewModel(fetchRandomBeer)
  }

  @Test
  fun `given success state, when fetchRandomBeer called`() {
    val successStatus = Resource.Success(createDummyBeerItem())

    coEvery { fetchRandomBeer.fetchBeer() } returns flow {
      emit(successStatus)
    }

    mainViewModel.fetchRandomBeer()
    mainViewModel.beerLiveData.observeForever { }

    assertEquals(successStatus, mainViewModel.beerLiveData.value)
  }

  @Test
  fun `given error state, when fetchRandomBeer called`() {
    val exception = Resource.DataError(Exception())
    val error: Resource<BeerItem> = exception

    coEvery { fetchRandomBeer.fetchBeer() } returns flow {
      emit(error)
    }

    mainViewModel.fetchRandomBeer()
    mainViewModel.beerLiveData.observeForever { }

    assertEquals(exception, mainViewModel.beerLiveData.value)
  }

}
