package com.example.myapplication.platform

import com.example.myapplication.domain.SudokuBoard
import com.example.myapplication.domain.SudokuGenerator
import javax.inject.Inject

/**
 * Currently only works for a 9x9 grid
 */
class SudokuGeneratorImpl @Inject constructor() : SudokuGenerator {

    val generator: Generator by lazy { Generator() }

    override fun createGame(): SudokuBoard {
        return generator.generate(55).toDomain()
    }

    private fun SudokuBoard.toDomain(): SudokuBoard {
        //Convert to kotlin impl of soduku game
        return this
    }

}