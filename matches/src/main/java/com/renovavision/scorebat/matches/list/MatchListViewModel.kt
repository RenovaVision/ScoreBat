package com.renovavision.scorebat.matches.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.renovavision.scorebat.common.network.Match
import com.renovavision.scorebat.common.repo.MatchesRepo
import com.renovavision.scorebat.matches.MatchesNavigator
import com.renovavision.scorebat.ui.AsyncEvent
import com.renovavision.scorebat.ui.Event
import com.renovavision.scorebat.ui.SyncEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

object LoadMatches : AsyncEvent
data class MatchClicked(val match: Match) : SyncEvent

data class State(
    val isLoading: Boolean,
    val showError: Boolean,
    val matches: List<Match> = emptyList()
)

class MatchListViewModel(
    private val matchesRepo: MatchesRepo,
    private val matchesNavigator: MatchesNavigator
) : ViewModel() {

    @ExperimentalCoroutinesApi
    private val _state = MutableStateFlow(State(isLoading = true, showError = false))

    @ExperimentalCoroutinesApi
    val state: StateFlow<State> = _state

    @ExperimentalCoroutinesApi
    fun dispatch(event: Event) {
        when (event) {
            is LoadMatches -> loadMatches()
            is MatchClicked -> matchesNavigator.openMatchDetails(event.match)
        }
    }

    @ExperimentalCoroutinesApi
    private fun loadMatches() {
        _state.value = State(isLoading = true, showError = false)
        viewModelScope.launch {
            matchesRepo.loadMatches()
                .catch { _state.value = State(isLoading = false, showError = true) }
                .collect {
                    when (it.isEmpty()) {
                        true -> _state.value = State(isLoading = false, showError = true)
                        else -> _state.value =
                            State(isLoading = false, showError = false, matches = it)
                    }
                }
        }
    }
}