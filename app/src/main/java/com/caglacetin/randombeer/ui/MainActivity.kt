package com.caglacetin.randombeer.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.caglacetin.randombeer.common.Resource
import com.caglacetin.randombeer.common.Resource.DataError
import com.caglacetin.randombeer.common.Resource.Loading
import com.caglacetin.randombeer.common.Resource.Success
import com.caglacetin.randombeer.common.observe
import com.caglacetin.randombeer.databinding.ActivityMainBinding
import com.caglacetin.randombeer.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

  private lateinit var binding: ActivityMainBinding
  private val viewModel: MainViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel.fetchRandomBeer()
  }

  override fun initViewBinding() {
    binding = ActivityMainBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)
  }

  override fun observeViewModel() {
    observe(viewModel.beerLiveData, ::setViews)
  }

  private fun setViews(status: Resource<BeerItem>) {
    binding.mainViewState = MainViewState(status)
    when (status) {
      is Loading -> MainViewState(Loading)
      is Success -> {
        val data = status.data
        MainViewState(Success(data))
        setUI(data)
      }
      is DataError -> MainViewState(DataError(status.exception))
    }
  }

  private fun setUI(data: BeerItem) {
    with(binding) {
      cvBeerName.setTextTitle(data.name.orEmpty())
      cvBeerTagline.setTextTitle(data.tagline)
      cvImage.setRadiusToImage(data.image)
      beerViewState = BeerViewState(data)
    }
  }
}
