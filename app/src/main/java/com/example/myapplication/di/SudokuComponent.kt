package com.example.myapplication.di

import android.app.Application
import com.example.myapplication.App
import com.example.myapplication.domain.Announcer
import com.example.myapplication.domain.DispatcherProvider
import com.example.myapplication.domain.DispatcherProviderImpl
import com.example.myapplication.domain.SudokuGenerator
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
}