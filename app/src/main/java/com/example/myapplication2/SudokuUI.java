package com.example.myapplication2;

import java.util.Scanner;

public class SudokuUI {

    public static void main(String[] args) {
        Sudoku sudoku = SudokuGenerator.generate();
        printBoard(sudoku.getBoard());

        Scanner scanner = new Scanner(System.in);
        while (!sudoku.isValid()) {
            System.out.println("Enter your move (row col num):");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            int num = scanner.nextInt();

            if (row < 0 || row >= 9 || col < 0 || col >= 9 || num < 1 || num > 9) {
                System.out.println("Invalid input. Please enter values between 0-8 for row/col and 1-9 for num.");
                continue;
            }

            sudoku.getBoard()[row][col] = num;
            printBoard(sudoku.getBoard());
        }

        if (SudokuSolver.solve(sudoku)) {
            System.out.println("Sudoku solved successfully!");
            printBoard(sudoku.getBoard());
        } else {
            System.out.println("This sudoku cannot be solved.");
        }

        scanner.close();
    }

    private static void printBoard(int[][] board) {
        for (int r = 0; r < board.length; r++) {
            for (int d = 0; d < board.length; d++) {
                System.out.print(board[r][d]);
                System.out.print(" ");
            }
            System.out.print("\n");

            if ((r + 1) % 3 == 0) {
                System.out.print("\n");
            }
        }
    }
}