package com.ugurkuyu.coinrank.model

data class Stats(
    var total: Int,
    var offset: Int,
    var limit: Int = 10,
    var order: String,
    var base: String
)