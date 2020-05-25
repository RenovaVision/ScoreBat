package com.renovavision.scorebat.matches.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.renovavision.scorebat.common.network.Match
import com.renovavision.scorebat.common.network.MatchesApi
import com.renovavision.scorebat.matches.MatchesNavigator
import com.renovavision.scorebat.ui.Dispatchable
import com.renovavision.scorebat.ui.Event
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

object LoadMatches : Event
data class MatchClicked(val match: Match) : Event

data class State(
    val isLoading: Boolean,
    val showError: Boolean,
    val matches: List<Match> = emptyList()
)

class MatchListViewModel(
    private val matchesApi: MatchesApi,
    private val matchesNavigator: MatchesNavigator
) : ViewModel() {

    private val state = MutableLiveData<State>()

    val networkState: LiveData<State>
        get() = state

    fun dispatch(dispatchable: Dispatchable) {
        when (dispatchable) {
            is LoadMatches -> loadMatches()
            is MatchClicked -> matchesNavigator.openMatchDetails(dispatchable.match)
        }
    }

    private fun loadMatches() {
        state.value = State(isLoading = true, showError = false)
        viewModelScope.launch(CoroutineExceptionHandler { _, _ ->
            state.value = State(isLoading = false, showError = true)
        }) {
            val matches = matchesApi.loadMatches()
            when (matches.isEmpty()) {
                true -> state.value = State(isLoading = false, showError = true)
                else -> state.value = State(isLoading = false, showError = false, matches = matches)
            }
        }
    }
}