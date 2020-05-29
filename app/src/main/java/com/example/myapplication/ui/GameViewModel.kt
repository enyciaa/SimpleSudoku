package com.example.myapplication.ui

import com.example.myapplication.di.ActivityScope
import com.example.myapplication.domain.*
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

    override fun onAction(action: UiAction) {
        super.onAction(action)
        when (action) {
            UiAction.NewGameClicked -> emitViewState(lastViewState.copy(sudokuBoard = sudokuGenerator.createGame()))
        }
    }

    data class ViewState(
            val sudokuBoard: SudokuBoard = SudokuBoard.emptyGrid()
    ) : MotherViewModel.ViewState

    sealed class UiAction : MotherViewModel.UiAction {
        object NewGameClicked : UiAction()
    }
}
