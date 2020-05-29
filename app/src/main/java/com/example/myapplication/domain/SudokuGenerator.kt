package com.example.myapplication.domain

interface SudokuGenerator {

    suspend fun createGame(): SudokuBoard
}