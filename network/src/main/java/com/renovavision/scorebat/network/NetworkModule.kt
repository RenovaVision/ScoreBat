package com.renovavision.scorebat.network

import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    internal fun provideRetrofit(
        @Named("cacheDir") httpCacheDir: File?,
        @Named("apiUrl") apiUrl: String
    ): Retrofit = Retrofit.Builder()
        .baseUrl(apiUrl)
        .callFactory(OkHttpClient.Builder().apply {
            httpCacheDir?.let { cache(Cache(it, (1024 * 1024 * 100))) } // 100MB
        }.build())
        .addConverterFactory(
            MoshiConverterFactory.create()
        ).build()

    @Provides
    internal fun provideMatchApi(retrofit: Retrofit) = retrofit.create(com.renovavision.scorebat.network.MatchesApi::class.java)
}