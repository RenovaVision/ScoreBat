package com.renovavision.scorebat.common.network

import com.squareup.moshi.JsonClass
import retrofit2.http.GET
import java.io.Serializable

interface MatchesApi {

    @GET("video-api/v1/")
    suspend fun loadMatches(): List<Match>
}

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
) : Serializable

@JsonClass(generateAdapter = true)
data class Team(
    val name: String,
    val url: String
) : Serializable

@JsonClass(generateAdapter = true)
data class Competition(
    val name: String,
    val id: String,
    val url: String
) : Serializable

@JsonClass(generateAdapter = true)
data class Video(
    val title: String,
    val embed: String
) : Serializable