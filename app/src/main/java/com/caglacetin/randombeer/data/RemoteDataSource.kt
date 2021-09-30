package com.caglacetin.randombeer.data

import com.caglacetin.randombeer.common.Resource
import com.caglacetin.randombeer.data.response.BeerData

interface RemoteDataSource {
  suspend fun fetchRandomBeer(): Resource<List<BeerData>>
}
