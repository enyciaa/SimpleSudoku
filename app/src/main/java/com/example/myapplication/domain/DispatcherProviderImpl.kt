package com.example.myapplication.domain

import dagger.Reusable
import kotlinx.coroutines.*
import javax.inject.Inject

@Reusable
class DispatcherProviderImpl @Inject constructor() : DispatcherProvider {

    override val main: CoroutineDispatcher = Dispatchers.Main

    override val background: CoroutineDispatcher = Dispatchers.IO
}
