package com.renovavision.scorebat.matches.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.renovavision.scorebat.common.network.Match
import com.renovavision.scorebat.common.repo.MatchesRepo
import com.renovavision.scorebat.matches.MatchesNavigator
import com.renovavision.scorebat.ui.Dispatchable
import com.renovavision.scorebat.ui.Event
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

object LoadMatches : Event
data class MatchClicked(val match: Match) : Event

data class State(
    val isLoading: Boolean,
    val showError: Boolean,
    val matches: List<Match> = emptyList()
)

class MatchListViewModel(
    private val matchesRepo: MatchesRepo,
    private val matchesNavigator: MatchesNavigator
) : ViewModel() {

    private val _state = MutableLiveData<State>()

    val state: LiveData<State> = _state

    fun dispatch(dispatchable: Dispatchable) {
        when (dispatchable) {
            is LoadMatches -> loadMatches()
            is MatchClicked -> matchesNavigator.openMatchDetails(dispatchable.match)
        }
    }

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