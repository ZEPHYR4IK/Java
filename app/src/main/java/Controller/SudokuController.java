package Controller;

import Model.Sudoku;
import Model.SudokuGenerator;
import Model.SudokuSolver;

public class SudokuController {
    private Sudoku sudoku;

    public SudokuController() {
        generateNewSudoku();
    }

    public void generateNewSudoku() {
        this.sudoku = SudokuGenerator.generate();
    }

    public Sudoku getSudoku() {
        return sudoku;
    }

    public boolean solveSudoku() {
        return SudokuSolver.solve(sudoku);
    }

    public boolean isSudokuValid() {
        return sudoku.isValid();
    }

    public boolean isSafe(int row, int col, int num) {
        return sudoku.isSafe(sudoku.getBoard(), row, col, num);
    }
}
