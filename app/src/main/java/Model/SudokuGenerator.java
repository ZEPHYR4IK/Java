package Model;

import java.util.Random;

public class SudokuGenerator {
    private static final Random RANDOM = new Random();

    public static Sudoku generate() {
        int[][] board = new int[9][9];
        fillBoard(board);
        return new Sudoku(board);
    }

    private static void fillBoard(int[][] board) {
        for (int i = 0; i < 9; i += 3) {
            fillBlock(board, i, i);
        }
        SudokuSolver.solve(board);
        removeNumbers(board);
    }

    private static void fillBlock(int[][] board, int row, int col) {
        boolean[] used = new boolean[10];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num;
                do {
                    num = RANDOM.nextInt(9) + 1;
                } while (used[num]);
                used[num] = true;
                board[row + i][col + j] = num;
            }
        }
    }

    private static void removeNumbers(int[][] board) {
        int count = 81 - 54; // Оставляем 54 заполненные ячейки
        while (count > 0) {
            int row = RANDOM.nextInt(9);
            int col = RANDOM.nextInt(9);
            if (board[row][col] != 0) {
                board[row][col] = 0;
                count--;
            }
        }
    }
}