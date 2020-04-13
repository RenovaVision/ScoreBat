package com.renovavision.scorebat.matches

import com.renovavision.scorebat.network.Match

data class State(
    val isLoading: Boolean,
    val showError: Boolean,
    val matches: List<Match> = emptyList()
)