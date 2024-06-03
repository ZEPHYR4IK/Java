package com.example.myapplication2;

public class Sudoku {
    private int[][] board;
    private static final int SIZE = 9;

    public Sudoku(int[][] board) {
        this.board = board;
    }

    public int getSize() {
        return SIZE;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public boolean isValid() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] != 0 && !isSafe(board, row, col, board[row][col])) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isSafe(int[][] board, int row, int col, int num) {
        for (int x = 0; x < SIZE; x++) {
            if (x != col && board[row][x] == num) {
                return false;
            }
        }

        for (int x = 0; x < SIZE; x++) {
            if (x != row && board[x][col] == num) {
                return false;
            }
        }

        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if ((i != row || j != col) && board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}
