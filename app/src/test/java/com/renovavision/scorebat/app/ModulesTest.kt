package com.renovavision.scorebat.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.renovavision.scorebat.common.network.Match
import org.junit.Rule
import org.junit.Test
import org.junit.experimental.categories.Category
import org.koin.test.AutoCloseKoinTest
import org.koin.test.category.CheckModuleTest
import org.koin.test.check.checkModules
import org.koin.core.logger.Level
import uk.co.jemos.podam.api.PodamFactoryImpl

@Category(CheckModuleTest::class)
class ModulesTest : AutoCloseKoinTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun testAppModules() {
        try {
            checkModules {
                // set missed params
                koin.setProperty<Match>("match", entityFactory())
                printLogger(Level.DEBUG)
                modules(appModules("https://www.fakeurl.com", null))
            }
        } catch (t: Throwable) {
            if (t.javaClass.canonicalName?.startsWith("org.koin.core.error") == true) {
                // Koin validation error - rethrow issue and fail test
                throw t
            }
            // ignore mocked ui issues
        }
    }

    private fun entityFactory() =
        PodamFactoryImpl().manufacturePojo(Match::class.java)
}