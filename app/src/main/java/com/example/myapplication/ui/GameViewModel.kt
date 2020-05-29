package com.example.myapplication.ui

import com.example.myapplication.R
import com.example.myapplication.di.ActivityScope
import com.example.myapplication.domain.*
import com.example.myapplication.ui.base.LifecycleReceiver
import com.example.myapplication.ui.base.MotherViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ActivityScope
class GameViewModel @Inject constructor(
        private val sudokuGenerator: SudokuGenerator,
        private val currentGameRepository: CurrentGameRepository,
        private val sudokuBoardEditor: SudokuBoardEditor,
        private val announcer: Announcer,
        private val resourceProvider: ResourceProvider,
        private val dispatcherProvider: DispatcherProvider
) : MotherViewModel<GameViewModel.ViewState, GameViewModel.UiAction>(dispatcherProvider) {

    override var lastViewState = ViewState()

    init {
        coroutineScope.launch {
            val sudokuBoard = withContext(dispatcherProvider.background) { currentGameRepository.fetchGame() }
            emitViewState(lastViewState.copy(sudokuBoard = sudokuBoard))
            sudokuBoardEditor.setSudokuBoard(sudokuBoard)
        }
    }

    override fun onAction(action: UiAction) {
        super.onAction(action)
        when (action) {
            UiAction.NewGameClicked -> {
                announcer.announce(
                        resourceProvider.getString(R.string.lose_game_warning),
                        resourceProvider.getString(R.string.lose_game_warning_confirmation)
                ) {
                    coroutineScope.launch {
                        val sudokuBoard = withContext(dispatcherProvider.background) { sudokuGenerator.createGame() }
                        emitViewState(lastViewState.copy(sudokuBoard = sudokuBoard))
                        sudokuBoardEditor.setSudokuBoard(sudokuBoard)
                        currentGameRepository.saveGame(sudokuBoard)
                    }
                }
            }
            is UiAction.NumberSelected -> {
                val newBoard = sudokuBoardEditor.updateNumber(action.number, action.cell)
                emitViewState(lastViewState.copy(sudokuBoard = newBoard))
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        val board = sudokuBoardEditor.getBoard()
        if (board != null) {
            currentGameRepository.saveGame(board)
        }

    }

    data class ViewState(
            val sudokuBoard: SudokuBoard = SudokuBoard.emptyGrid()
    ) : MotherViewModel.ViewState

    sealed class UiAction : MotherViewModel.UiAction {
        object NewGameClicked : UiAction()
        class NumberSelected(
                val number: Int,
                val cell: Pair<Int, Int>
        ) : UiAction()
    }
}
