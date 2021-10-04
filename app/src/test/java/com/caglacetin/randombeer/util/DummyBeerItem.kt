package com.caglacetin.randombeer.util

import com.caglacetin.randombeer.ui.BeerItem

object DummyBeerItem {
  fun createDummyBeerItem()=
    BeerItem(
      name = "Beer Name",
      tagline = "Beer Tagline",
      image = "https://picsum.photos/id/1004/5616/3744"
    )
}
