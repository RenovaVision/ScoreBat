package com.renovavision.scorebat.common.dispatcher;

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatcherProvider {
    fun ioDispatcher(): CoroutineDispatcher
}
