package com.renovavision.scorebat.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Team(
    val name: String,
    val url: String
)