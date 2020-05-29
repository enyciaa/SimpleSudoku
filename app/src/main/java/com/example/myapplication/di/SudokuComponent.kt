package com.example.myapplication.di

import android.app.Application
import com.example.myapplication.App
import com.example.myapplication.domain.*
import com.example.myapplication.platform.ResourceProviderImpl
import com.example.myapplication.platform.SudokuGeneratorImpl
import com.example.myapplication.ui.AnnouncerImpl
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScope
@Component(
        modules = [
            AndroidInjectionModule::class,
            AndroidSupportInjectionModule::class,
            ApplicationScopedModule::class,
            SingleActivityModule::class
        ]
)
interface SudokuComponent : AndroidInjector<App> {

    @Component.Factory
    interface Builder {

        fun build(@BindsInstance application: Application): SudokuComponent
    }
}

@Module
interface ApplicationScopedModule {

    @Binds
    fun dispatcherProvider(dispatcherProviderImpl: DispatcherProviderImpl): DispatcherProvider

    @Binds
    fun sudokuGenerator(sudokuGeneratorImpl: SudokuGeneratorImpl): SudokuGenerator

    @Binds
    fun resourceProvider(resourceProviderImpl: ResourceProviderImpl): ResourceProvider
}