package com.example.myapplication.platform;

import com.example.myapplication.domain.SudokuGame;

import java.util.Random;

/**
 * A Generator to generate random Sudoku {@link SudokuGame} instances.
 *
 * Taken from:
 * https://github.com/a11n/sudoku
 */
public class Generator {
  private Solver solver;

  /**
   * Constructs a new Generator instance.
   */
  public Generator() {
    this.solver = new Solver();
  }

  /**
   * Generates a random {@link SudokuGame} instance with the given number of empty {@link SudokuGame.Cell}s.
   * <br><br>
   * Note: The complexity for a human player increases with an higher amount of empty {@link SudokuGame.Cell}s.
   * @param numberOfEmptyCells the number of empty {@link SudokuGame.Cell}s
   * @return a randomly filled Sudoku {@link SudokuGame} with the given number of empty {@link SudokuGame.Cell}s
   */
  public SudokuGame generate(int numberOfEmptyCells) {
    SudokuGame sudokuGame = generate();

    eraseCells(sudokuGame, numberOfEmptyCells);

    return sudokuGame;
  }

  private void eraseCells(SudokuGame sudokuGame, int numberOfEmptyCells) {
    Random random = new Random();
    for (int i = 0; i < numberOfEmptyCells; i++) {
      int randomRow = random.nextInt(9);
      int randomColumn = random.nextInt(9);

      SudokuGame.Cell cell = sudokuGame.getCell(randomRow, randomColumn);
      if (!cell.isEmpty()) {
        cell.setValue(0);
      } else {
        i--;
      }
    }
  }

  private SudokuGame generate() {
    SudokuGame sudokuGame = SudokuGame.emptyGrid();

    solver.solve(sudokuGame);

    return sudokuGame;
  }
}
