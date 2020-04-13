package com.renovavision.scorebat.activity

import androidx.lifecycle.ViewModelProvider
import com.renovavision.scorebat.inject.ViewModelFactory
import com.renovavision.scorebat.matches.MatchesModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {

    @Binds
    fun viewModelProviderFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector(
        modules = [
            MatchesModule::class,
            NavigationModule::class
        ]
    )
    fun mainActivity(): MainActivity
}