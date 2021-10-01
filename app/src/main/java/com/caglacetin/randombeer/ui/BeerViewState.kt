package com.caglacetin.randombeer.ui

data class BeerViewState(
  val beerItem: BeerItem
) {

  fun getBeerName() = beerItem.name

  fun getTagline() = beerItem.tagline

  fun getImageUrl() = beerItem.image

}
