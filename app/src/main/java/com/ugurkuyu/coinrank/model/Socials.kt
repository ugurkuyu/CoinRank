package com.ugurkuyu.coinrank.model

import java.io.Serializable

data class Socials(
    var name: String,
    var url: String,
    var type: String
): Serializable {
}