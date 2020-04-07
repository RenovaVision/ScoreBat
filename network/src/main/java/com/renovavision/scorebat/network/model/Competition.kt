package com.renovavision.scorebat.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Competition(
    val name: String,
    val id: String,
    val url: String
)