package com.example.myapplication.platform;

import com.example.myapplication.domain.SudokuGame;

import java.util.Optional;
import java.util.Random;

/**
 * A Solver is capable of solving a given Sudoku {@link SudokuGame}.
 *
 * Taken from:
 * https://github.com/a11n/sudoku
 */
public class Solver {
  private static final int EMPTY = 0;
  
  private final int[] values;

  /**
   * Constructs a new Solver instance.
   */
  public Solver() {
    this.values = generateRandomValues();
  }

  /**
   * Solves a given {@link SudokuGame} using backtracking.
   *
   * @param sudokuGame the {@link SudokuGame} to solve
   * @throws IllegalStateException in case the provided {@link SudokuGame} is invalid.
   */
  public void solve(SudokuGame sudokuGame) {
    boolean solvable = solve(sudokuGame, sudokuGame.getFirstEmptyCell());

    if (!solvable) {
      throw new IllegalStateException("The provided grid is not solvable.");
    }
  }

  private boolean solve(SudokuGame sudokuGame, Optional<SudokuGame.Cell> cell) {
    if (!cell.isPresent()) {
      return true;
    }

    for (int value : values) {
      if (sudokuGame.isValidValueForCell(cell.get(), value)) {
        cell.get().setValue(value);
        if (solve(sudokuGame, sudokuGame.getNextEmptyCellOf(cell.get()))) return true;
        cell.get().setValue(EMPTY);
      }
    }

    return false;
  }

  private int[] generateRandomValues() {
    int[] values = { EMPTY, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    Random random = new Random();
    for (int i = 0, j = random.nextInt(9), tmp = values[j]; i < values.length;
        i++, j = random.nextInt(9), tmp = values[j]) {
      if(i == j) continue;
      
      values[j] = values[i];
      values[i] = tmp;
    }

    return values;
  }
}
