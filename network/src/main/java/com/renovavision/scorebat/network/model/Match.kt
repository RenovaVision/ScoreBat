package com.renovavision.scorebat.network.model

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Match(
    val title: String,
    val embed: String,
    val url: String,
    val thumbnail: String,
    val date: String,
    val side1: Team,
    val side2: Team,
    val competition: Competition,
    val videos: List<Video>
): Serializable