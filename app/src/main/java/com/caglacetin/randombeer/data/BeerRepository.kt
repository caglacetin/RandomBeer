package com.caglacetin.randombeer.data

import com.caglacetin.randombeer.common.Resource
import com.caglacetin.randombeer.data.response.BeerData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class BeerRepository @Inject constructor(
  private val remoteRepository: RemoteDataRepository,
  private val ioDispatcher: CoroutineContext
) {

  suspend fun fetchBeer(): Flow<Resource<List<BeerData>>> {
    return flow {
      emit(remoteRepository.fetchRandomBeer())
    }.flowOn(ioDispatcher)
  }

}
