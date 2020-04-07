package com.renovavision.scorebat.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Video(
    val title: String,
    val embed: String
)