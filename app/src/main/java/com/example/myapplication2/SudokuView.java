package com.example.myapplication2;

import android.content.Context;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

public class SudokuView {
    private Context context;
    private GridLayout sudokuGrid;
    private TextView[][] cells;
    private boolean[][] isCellFixed;
    private int selectedRow = -1;
    private int selectedCol = -1;

    public SudokuView(Context context, GridLayout sudokuGrid) {
        this.context = context;
        this.sudokuGrid = sudokuGrid;
        cells = new TextView[9][9];
        isCellFixed = new boolean[9][9];
        initializeSudokuGrid();
    }

    public void updateBoard(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int num = board[row][col];
                if (num != 0) {
                    cells[row][col].setText(String.valueOf(num));
                } else {
                    cells[row][col].setText("");
                }
            }
        }
    }

    private void initializeSudokuGrid() {
        int cellSize = context.getResources().getDisplayMetrics().widthPixels / 11;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                TextView cell = new TextView(context);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.rowSpec = GridLayout.spec(row, 1f);
                params.columnSpec = GridLayout.spec(col, 1f);
                params.width = cellSize;
                params.height = cellSize;

                params.setMargins(1, 1, 1, 1);
                cell.setLayoutParams(params);
                cell.setGravity(android.view.Gravity.CENTER);
                cell.setTextSize(18);
                cell.setBackgroundResource(R.drawable.cell_border);

                cells[row][col] = cell;
                sudokuGrid.addView(cell);
            }
        }
    }

    public void setCellValue(int row, int col, int value) {
        cells[row][col].setText(String.valueOf(value));
        isCellFixed[row][col] = true;
    }

    public void clearCellValue(int row, int col) {
        cells[row][col].setText("");
        isCellFixed[row][col] = false;
    }

    public void setSelectedCell(int row, int col) {
        selectedRow = row;
        selectedCol = col;
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public int getSelectedCol() {
        return selectedCol;
    }

    public boolean isCellFixed(int row, int col) {
        return isCellFixed[row][col];
    }
}
