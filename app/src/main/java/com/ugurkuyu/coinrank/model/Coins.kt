package com.ugurkuyu.coinrank.model

import java.io.Serializable

data class Coins(
    var id: Int,
    var symbol: String,
    var name: String,
    var description: String,
    var color: String?,
    var iconUrl: String,
    var websiteUrl: String,
    var socials: List<Socials>,
    var volume: Number,
    var marketCap: Number,
    var price: String,
    var totalSupply: Number,
    var rank: Int,
    var history: ArrayList<String>,
    var allTimeHigh: AllTimeHigh
): Serializable