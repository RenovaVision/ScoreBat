package com.renovavision.scorebat.common

import com.renovavision.scorebat.common.network.MatchesApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File

fun commonModule(apiUrl: String, httpCacheDir: File?) = module {
    single {
        Retrofit.Builder()
            .baseUrl(apiUrl)
            .callFactory(OkHttpClient.Builder().apply {
                httpCacheDir?.let { cache(Cache(it, (1024 * 1024 * 100))) } // 100MB
            }.build())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
    factory { (get<Retrofit>()).create(MatchesApi::class.java) }
}