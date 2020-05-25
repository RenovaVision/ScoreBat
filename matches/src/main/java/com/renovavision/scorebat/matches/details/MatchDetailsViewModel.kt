package com.renovavision.scorebat.matches.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.renovavision.scorebat.common.network.Match
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.Serializable

data class State(val html: String?) : Serializable

class MatchDetailsViewModel(
    private val handle: SavedStateHandle,
    private val match: Match
) : ViewModel() {

    @ExperimentalCoroutinesApi
    private val _state = MutableStateFlow(State("null"))

    @ExperimentalCoroutinesApi
    val state: StateFlow<State> = _state

    init {
        handle.get<State>("state")?.let {
            _state.value = it
        } ?: run {
            _state.value = State(match.videos[0].embed)
        }
    }
}