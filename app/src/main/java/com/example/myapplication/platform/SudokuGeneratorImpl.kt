package com.example.myapplication.platform

import com.example.myapplication.domain.SudokuGame
import com.example.myapplication.domain.SudokuGenerator
import javax.inject.Inject

/**
 * Currently only works for a 9x9 grid
 */
class SudokuGeneratorImpl @Inject constructor() : SudokuGenerator {

    val generator: Generator by lazy { Generator() }

    override fun createGame(): SudokuGame {
        return generator.generate(10).toDomain()
    }

    private fun SudokuGame.toDomain(): SudokuGame {
        //Convert to kotlin impl of soduku game
        return this
    }

}