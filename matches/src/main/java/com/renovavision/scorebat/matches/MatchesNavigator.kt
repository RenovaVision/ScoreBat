package com.renovavision.scorebat.matches

import com.renovavision.scorebat.common.network.Match

interface MatchesNavigator {

    fun openMatchDetails(match: Match)
}