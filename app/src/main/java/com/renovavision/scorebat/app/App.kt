package com.renovavision.scorebat.app

import android.app.Application
import com.renovavision.scorebat.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            fragmentFactory()
            modules(appModules(BuildConfig.API_URL, cacheDir))
        }
    }
}