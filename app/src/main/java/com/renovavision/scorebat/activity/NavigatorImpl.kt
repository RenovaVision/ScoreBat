package com.renovavision.scorebat.activity

import androidx.navigation.findNavController
import com.renovavision.scorebat.R
import com.renovavision.scorebat.common.network.Match
import com.renovavision.scorebat.matches.MatchesNavigator
import com.renovavision.scorebat.matches.list.MatchListFragmentDirections
import com.renovavision.scorebat.ui.navigation.Navigator

class NavigatorImpl : Navigator, MatchesNavigator {

    private var activity: MainActivity? = null

    fun bind(mainActivity: MainActivity) {
        this.activity = mainActivity
    }

    fun unbind() {
        this.activity = null
    }

    override fun navBack() {
        activity?.apply {
            runOnUiThread {
                findNavController(R.id.navHostFragment).popBackStack()
            }
        }
    }

    override fun openMatchDetails(match: Match) {
        activity?.apply {
            runOnUiThread {
                findNavController(R.id.navHostFragment).navigate(
                    MatchListFragmentDirections.navigateToMatchDetails(match)
                )
            }
        }
    }
}

