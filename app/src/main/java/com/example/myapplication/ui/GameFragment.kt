package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.ui.base.LifecycleReceiver
import com.example.myapplication.ui.base.MotherFragment
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GameFragment : MotherFragment() {

    @Inject lateinit var gameViewModel: GameViewModel

    override fun lifecycleReceivers(): List<LifecycleReceiver> {
        return listOf(gameViewModel)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(
            view: View,
            savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        newGameButton.setOnClickListener { gameViewModel.onAction(GameViewModel.UiAction.NewGameClicked) }
    }

    override fun onStart() {
        super.onStart()
        gameViewModel.viewStateStream()
                .onEach { viewState ->
                    sudokuBoard.setBoard(viewState.sudokuBoard)
                }
                .launchIn(coroutineScope)
    }
}
