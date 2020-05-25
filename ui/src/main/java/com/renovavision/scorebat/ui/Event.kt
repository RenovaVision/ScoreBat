package com.renovavision.scorebat.ui

interface Event
interface SyncEvent : Event
interface AsyncEvent : Event
typealias EventHandler = (event: Event) -> Unit