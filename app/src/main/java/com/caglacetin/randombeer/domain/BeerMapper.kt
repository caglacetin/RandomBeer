package com.caglacetin.randombeer.domain

import com.caglacetin.randombeer.common.Mapper
import com.caglacetin.randombeer.data.response.BeerData
import com.caglacetin.randombeer.ui.BeerItem
import javax.inject.Inject

class BeerMapper @Inject constructor() : Mapper<BeerData, BeerItem> {

  override fun mapFrom(beerData: BeerData): BeerItem =
      BeerItem(
        id = beerData.id,
        name = beerData.name
      )
}
