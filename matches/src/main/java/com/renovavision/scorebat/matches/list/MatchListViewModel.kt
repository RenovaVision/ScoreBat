package com.renovavision.scorebat.matches.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.renovavision.scorebat.matches.State
import com.renovavision.scorebat.network.MatchesApi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class MatchListViewModel @Inject constructor(private val matchesApi: MatchesApi) :
    ViewModel() {

    private val state = MutableLiveData<State>()
    val networkState: LiveData<State>
        get() = state

    fun loadMatches() {
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