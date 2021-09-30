package com.caglacetin.randombeer.domain

import com.caglacetin.randombeer.common.Resource
import com.caglacetin.randombeer.common.map
import com.caglacetin.randombeer.data.BeerRepository
import com.caglacetin.randombeer.ui.BeerItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchRandomBeer @Inject constructor(
  private val repository: BeerRepository,
  private val mapper: BeerMapper
) {
  suspend fun fetchImages(): Flow<Resource<BeerItem>> =
    repository.fetchBeer().map { resource ->
      resource.map { response ->
        mapper.mapFrom(response)
      }
    }
}
