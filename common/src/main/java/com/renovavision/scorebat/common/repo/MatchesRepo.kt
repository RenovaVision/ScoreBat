package com.renovavision.scorebat.common.repo

import com.renovavision.scorebat.common.dispatcher.CoroutineDispatcherProvider
import com.renovavision.scorebat.common.network.MatchesApi
import kotlinx.coroutines.flow.flow

class MatchesRepo(
    private val api: MatchesApi,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) {

    fun loadMatches() = flow {
        emit(api.loadMatches())
    }
}