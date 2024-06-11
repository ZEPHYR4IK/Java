package com.example.myapplication2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.Controller.SudokuController;
import com.example.myapplication2.View.SudokuView;

public class MainActivity extends AppCompatActivity {
    private SudokuController controller;
    private SudokuView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new SudokuController();
        view = new SudokuView(this, controller);

        setupSolveButton();
        setupRefreshButton();
    }

    private void setupSolveButton() {
        Button solveButton = new Button(this);
        solveButton.setText("Решить");
        solveButton.setBackgroundResource(R.drawable.blue_button_background);
        solveButton.setTextColor(getResources().getColor(android.R.color.white));

        LinearLayout.LayoutParams solveButtonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        solveButton.setLayoutParams(solveButtonParams);

        solveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (controller.isSudokuValid()) {
                    if (controller.solveSudoku()) {
                        Toast.makeText(MainActivity.this, "Судоку успешно решено!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Эта судоку не может быть решена.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Судоку содержит ошибки.", Toast.LENGTH_SHORT).show();
                }
                view.updateBoard();
            }
        });

        LinearLayout mainLayout = findViewById(R.id.mainLayout);
        mainLayout.addView(solveButton);
    }

    private void setupRefreshButton() {
        Button refreshButton = new Button(this);
        refreshButton.setText("Заново");
        refreshButton.setBackgroundResource(R.drawable.red_button_background);
        refreshButton.setTextColor(getResources().getColor(android.R.color.white));

        LinearLayout.LayoutParams refreshButtonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        refreshButtonParams.setMargins(0, 20, 0, 0);
        refreshButton.setLayoutParams(refreshButtonParams);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.generateNewSudoku();
                view = new SudokuView(MainActivity.this, controller);
            }
        });

        LinearLayout mainLayout = findViewById(R.id.mainLayout);
        mainLayout.addView(refreshButton);
    }
}