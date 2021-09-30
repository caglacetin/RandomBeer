package com.caglacetin.randombeer.common

interface Mapper<R, D> {
  fun mapFrom(type: R): D
}
