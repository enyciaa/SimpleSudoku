package com.example.myapplication.domain

import kotlinx.coroutines.*

interface DispatcherProvider{

    val main: CoroutineDispatcher

    val background: CoroutineDispatcher
}
