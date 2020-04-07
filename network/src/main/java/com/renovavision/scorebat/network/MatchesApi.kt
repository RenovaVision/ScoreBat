package com.renovavision.scorebat.network

import com.renovavision.scorebat.network.model.Match
import retrofit2.Response
import retrofit2.http.GET

interface MatchesApi {

    @GET("video-api/v1/")
    suspend fun loadMatches(): List<Match>
}