package com.renovavision.scorebat.matches

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.renovavision.scorebat.inject.FragmentKey
import com.renovavision.scorebat.inject.ViewModelKey
import com.renovavision.scorebat.matches.details.MatchDetailsFragment
import com.renovavision.scorebat.matches.details.MatchDetailsViewModel
import com.renovavision.scorebat.matches.list.MatchListFragment
import com.renovavision.scorebat.matches.list.MatchListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MatchesModule {

    @Binds
    @IntoMap
    @FragmentKey(MatchListFragment::class)
    fun matchListFragment(fragment: MatchListFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(MatchListViewModel::class)
    fun matchListViewModel(viewModel: MatchListViewModel): ViewModel

    @Binds
    @IntoMap
    @FragmentKey(MatchDetailsFragment::class)
    fun matchDetailsFragment(fragment: MatchDetailsFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(MatchDetailsViewModel::class)
    fun matchDetailsViewModel(viewModel: MatchDetailsViewModel): ViewModel
}