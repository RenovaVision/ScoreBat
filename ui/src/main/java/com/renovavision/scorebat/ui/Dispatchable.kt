package com.renovavision.scorebat.ui

interface Dispatchable
interface Event : Dispatchable
typealias Dispatch = (dispatchable: Dispatchable) -> Unit