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
    //listenSliders()
    //updateTaglineVisibility()

  }

  private fun updateTaglineVisibility() {
    with(binding) {
     /* ivBeerImage.setOnClickListener {
        if(tvBeerTagline.isVisible) {
          tvBeerTagline.visibility = GONE
          etTaglineColor.visibility = GONE
          etTaglineSize.visibility = GONE
        } else {
          tvBeerTagline.visibility = VISIBLE
          etTaglineColor.visibility = VISIBLE
          etTaglineSize.visibility = VISIBLE
        }
      }*/
    }
  }


  //TODO: if image url null, show text message to user.

  /*private fun listenSliders() {
    binding.sliderImageRadius.addOnChangeListener { slider, value, fromUser ->
      val shape = binding.ivBeerImage.shapeAppearanceModel.toBuilder()
        .setAllCornerSizes(value)
        .build()

      binding.ivBeerImage.shapeAppearanceModel = shape
    }
  }*/

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
        binding.cvBeerName.setTextTitle(status.data.name.orEmpty())
        binding.cvBeerTagline.setTextTitle(status.data.tagline)
        binding.cvImage.setRadiusToImage(status.data.image)
        MainViewState(Success(status.data))
        binding.beerViewState = BeerViewState(status.data)
      }
      is DataError -> MainViewState(DataError(status.exception))
    }
  }

}
