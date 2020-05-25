package com.renovavision.scorebat.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.renovavision.scorebat.R
import org.koin.android.ext.android.inject
import org.koin.androidx.fragment.android.setupKoinFragmentFactory

class MainActivity : AppCompatActivity() {

    private val navigatorImpl: NavigatorImpl by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory()
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigatorImpl.bind(this)
    }

    override fun onDestroy() {
        navigatorImpl.unbind()
        super.onDestroy()
    }
}
