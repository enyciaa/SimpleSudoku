package com.example.myapplication.ui

import com.example.myapplication.di.ActivityScope
import com.example.myapplication.domain.Announcer
import com.example.myapplication.domain.CurrentGameRepository
import com.example.myapplication.domain.DispatcherProvider
import com.example.myapplication.domain.SudokuGenerator
import com.example.myapplication.ui.base.MotherViewModel
import javax.inject.Inject

@ActivityScope
class GameViewModel @Inject constructor(
        private val sudokuGenerator: SudokuGenerator,
        private val currentGameRepository: CurrentGameRepository,
        private val announcer: Announcer,
        private val dispatcherProvider: DispatcherProvider
) : MotherViewModel<GameViewModel.ViewState, GameViewModel.UiAction>(dispatcherProvider) {

    override var lastViewState = ViewState()

    data class ViewState(
            val loadingIsVisible: Boolean = false
    ) : MotherViewModel.ViewState

    sealed class UiAction : MotherViewModel.UiAction {
        class CurrencySelected(val currency: String) : UiAction()
    }
}
