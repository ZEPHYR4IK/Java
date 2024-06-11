package com.example.myapplication2.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.R;
import com.example.myapplication2.Controller.SudokuController;
import com.example.myapplication2.Model.Sudoku;

public class SudokuView {
    private TextView[][] cells = new TextView[9][9];
    private Context context;
    private SudokuController controller;
    private int selectedRow = -1;
    private int selectedCol = -1;

    public SudokuView(Context context, SudokuController controller) {
        this.context = context;
        this.controller = controller;
        initializeSudokuGrid();
    }

    private void initializeSudokuGrid() {
        AppCompatActivity activity = (AppCompatActivity) context;
        GridLayout sudokuGrid = activity.findViewById(R.id.sudokuGrid);

        int cellSize = context.getResources().getDisplayMetrics().widthPixels / 11;

        Sudoku sudoku = controller.getSudoku();
        int[][] board = sudoku.getBoard();

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

                if (board[row][col] != 0) {
                    cell.setText(String.valueOf(board[row][col]));
                    cell.setTextColor(context.getResources().getColor(android.R.color.black));
                } else {
                    cell.setText("");
                    cell.setTextColor(context.getResources().getColor(android.R.color.darker_gray));
                    int finalRow = row;
                    int finalCol = col;
                    cell.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            selectedRow = finalRow;
                            selectedCol = finalCol;
                            showNumberInputDialog();
                        }
                    });
                }

                cells[row][col] = cell;
                sudokuGrid.addView(cell);
            }
        }
    }
    public void updateBoard() {
        Sudoku sudoku = controller.getSudoku();
        int[][] board = sudoku.getBoard();

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

    private void showNumberInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Введите число");

        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    int num = Integer.parseInt(input.getText().toString());
                    if (num < 1 || num > 9) {
                        Toast.makeText(context, "Пожалуйста, введите число от 1 до 9.", Toast.LENGTH_SHORT).show();
                    } else {
                        controller.getSudoku().getBoard()[selectedRow][selectedCol] = num;
                        cells[selectedRow][selectedCol].setText(String.valueOf(num));
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(context, "Пожалуйста, введите правильное число.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}

