package com.caglacetin.randombeer.data

import com.caglacetin.randombeer.common.Resource
import com.caglacetin.randombeer.data.base.BaseDataSource
import com.caglacetin.randombeer.data.response.BeerData
import javax.inject.Inject

class RemoteDataRepository @Inject constructor(
  private val service: PunkApiService,
): BaseDataSource(), RemoteDataSource {

  override suspend fun fetchRandomBeer(): Resource<List<BeerData>> {
    val response = processCall {
      service.fetchRandomBeer()
    }
    return response
  }
}
