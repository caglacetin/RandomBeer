package com.caglacetin.randombeer.data.response

import com.squareup.moshi.Json

data class BeerData(
  val name: String,
  val tagline: String,
  @Json(name = "image_url")
  val imageUrl: String?
)
