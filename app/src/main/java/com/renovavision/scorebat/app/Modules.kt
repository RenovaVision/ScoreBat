package com.renovavision.scorebat.app

import com.renovavision.scorebat.activity.NavigatorImpl
import com.renovavision.scorebat.common.commonModule
import com.renovavision.scorebat.common.dispatcher.CoroutineDispatcherProvider
import com.renovavision.scorebat.matches.MatchesNavigator
import com.renovavision.scorebat.matches.details.MatchDetailsFragment
import com.renovavision.scorebat.matches.list.MatchListFragment
import com.renovavision.scorebat.matches.list.MatchListViewModel
import com.renovavision.scorebat.ui.navigation.Navigator
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.io.File

fun appModules(apiUrl: String, httpCacheDir: File?) =
    listOf(commonModule(apiUrl, httpCacheDir), appModule, navigationModule, mainModule)

val appModule = module {
    single<CoroutineDispatcherProvider> {
        object : CoroutineDispatcherProvider {
            override fun ioDispatcher() = Dispatchers.IO
        }
    }
}

val navigationModule = module {
    val appNavigator = NavigatorImpl()
    single { appNavigator }
    single<Navigator> { appNavigator }
    single<MatchesNavigator> { appNavigator }
}

val mainModule = module {
    viewModel { MatchListViewModel(get(), get()) }
    fragment { MatchListFragment() }
    fragment { MatchDetailsFragment() }
}

