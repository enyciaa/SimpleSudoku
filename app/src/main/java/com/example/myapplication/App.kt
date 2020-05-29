package com.example.myapplication

import com.example.myapplication.di.DaggerSudokuComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<App> {
        return DaggerSudokuComponent.factory()
                .build(this)
    }
}
