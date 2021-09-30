package com.caglacetin.randombeer.data

import com.caglacetin.randombeer.data.response.BeerData
import retrofit2.Response
import retrofit2.http.GET

interface PunkApiService {

  @GET("beers/random")
  suspend fun fetchRandomBeer(): Response<List<BeerData>>

}