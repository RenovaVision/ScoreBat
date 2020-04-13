package com.renovavision.scorebat.activity

import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.renovavision.scorebat.R
import com.renovavision.scorebat.inject.FragmentKey
import com.renovavision.scorebat.matches.list.MatchListFragmentDirections
import com.renovavision.scorebat.network.Match
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module
internal object NavigationModule {

    @Provides
    @IntoMap
    @FragmentKey(NavHostFragment::class)
    fun navHostFragment(): Fragment = NavHostFragment()

    @Provides
    @Named("navMatchesListToMatchDetails")
    fun navMatchesListToMatchDetails(mainActivity: MainActivity): (match: Match) -> Unit =
        {
            mainActivity.findNavController(R.id.navHostFragment).navigate(
                MatchListFragmentDirections.navigateToMatchDetails(it)
            )
        }

    @Provides
    @Named("navBack")
    fun navBack(mainActivity: MainActivity): () -> Boolean = {
        mainActivity.findNavController(R.id.navHostFragment).popBackStack()
    }
}