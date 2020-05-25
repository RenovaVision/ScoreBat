package com.renovavision.scorebat.ui

interface ViewEvent

interface Dispatchable
interface Event : Dispatchable
typealias Dispatch = (dispatchable: Dispatchable) -> Unit