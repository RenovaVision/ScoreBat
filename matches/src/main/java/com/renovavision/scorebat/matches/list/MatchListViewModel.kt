package com.renovavision.scorebat.matches.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.renovavision.scorebat.network.Match
import com.renovavision.scorebat.network.MatchesApi
import com.renovavision.scorebat.ui.Dispatchable
import com.renovavision.scorebat.ui.Event
import com.renovavision.scorebat.ui.SingleLiveEvent
import com.renovavision.scorebat.ui.ViewEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

data class NavigateToMatchDetails(val match: Match) : ViewEvent

object LoadMatches : Event
data class MatchClicked(val match: Match) : Event

data class State(
    val isLoading: Boolean,
    val showError: Boolean,
    val matches: List<Match> = emptyList()
)

class MatchListViewModel @Inject constructor(private val matchesApi: MatchesApi) :
    ViewModel() {

    private val state = MutableLiveData<State>()
    private val actions = SingleLiveEvent<ViewEvent>()

    val networkState: LiveData<State>
        get() = state
    val clickEvent: LiveData<ViewEvent>
        get() = actions

    fun dispatch(dispatchable: Dispatchable) {
        when (dispatchable) {
            is LoadMatches -> loadMatches()
            is MatchClicked -> actions.value = NavigateToMatchDetails(dispatchable.match)
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