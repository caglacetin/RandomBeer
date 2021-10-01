package com.caglacetin.randombeer.ui

data class BeerViewState(
  val beerItem: BeerItem
) {

  fun getBeerName(): String {
    return beerItem.name
  }
}
