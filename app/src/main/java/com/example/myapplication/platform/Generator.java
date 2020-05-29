package com.example.myapplication.platform;

import com.example.myapplication.domain.SudokuBoard;

import java.util.Random;

/**
 * A Generator to generate random Sudoku {@link SudokuBoard} instances.
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
   * Generates a random {@link SudokuBoard} instance with the given number of empty {@link SudokuBoard.Cell}s.
   * <br><br>
   * Note: The complexity for a human player increases with an higher amount of empty {@link SudokuBoard.Cell}s.
   * @param numberOfEmptyCells the number of empty {@link SudokuBoard.Cell}s
   * @return a randomly filled Sudoku {@link SudokuBoard} with the given number of empty {@link SudokuBoard.Cell}s
   */
  public SudokuBoard generate(int numberOfEmptyCells) {
    SudokuBoard sudokuBoard = generate();

    eraseCells(sudokuBoard, numberOfEmptyCells);

    return sudokuBoard;
  }

  private void eraseCells(SudokuBoard sudokuBoard, int numberOfEmptyCells) {
    Random random = new Random();
    for (int i = 0; i < numberOfEmptyCells; i++) {
      int randomRow = random.nextInt(9);
      int randomColumn = random.nextInt(9);

      SudokuBoard.Cell cell = sudokuBoard.getCell(randomRow, randomColumn);
      if (!cell.isEmpty()) {
        cell.setValue(0);
      } else {
        i--;
      }
    }
  }

  private SudokuBoard generate() {
    SudokuBoard sudokuBoard = SudokuBoard.emptyGrid();

    solver.solve(sudokuBoard);

    return sudokuBoard;
  }
}
