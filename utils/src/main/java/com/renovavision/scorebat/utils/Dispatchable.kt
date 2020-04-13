package com.renovavision.scorebat.utils

interface ViewEvent

interface Dispatchable
interface Event : Dispatchable
typealias Dispatch = (dispatchable: Dispatchable) -> Unit