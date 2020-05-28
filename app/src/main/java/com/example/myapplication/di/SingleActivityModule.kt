package com.example.myapplication.di

import com.example.myapplication.domain.Announcer
import com.example.myapplication.ui.SudokuActivity
import com.example.myapplication.ui.AnnouncerImpl
import com.example.myapplication.ui.GameFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface SingleActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(
            modules = [
                FragmentModule::class,
                ActivityScopedModule::class
            ]
    )
    fun sudokuActivity(): SudokuActivity
}

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun gameFragment(): GameFragment
}

@Module
interface ActivityScopedModule {

    @Binds
    fun announcer(announcerImpl: AnnouncerImpl): Announcer
}
