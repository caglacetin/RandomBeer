package com.caglacetin.randombeer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caglacetin.randombeer.common.Resource
import com.caglacetin.randombeer.domain.FetchRandomBeer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val usaCase: FetchRandomBeer
): ViewModel() {

  private val beerLiveDataPrivate = MutableLiveData<Resource<BeerItem>>()
  val beerLiveData: LiveData<Resource<BeerItem>> get() = beerLiveDataPrivate

  fun fetchRandomBeer() =
    viewModelScope.launch {
      beerLiveDataPrivate.value = Resource.Loading
      usaCase.fetchBeer().collect {
        beerLiveDataPrivate.value = it
      }
    }
}
