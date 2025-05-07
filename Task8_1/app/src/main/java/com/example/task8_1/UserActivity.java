package com.example.task8_1;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class UserActivity extends AppCompatActivity {
    private int number;
    private EditText numberInput;
    private EditText numberInputSave;
    private TextView answer;
    private LinearLayout saveLayout;
    private LinearLayout checkLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        saveLayout = findViewById(R.id.saveLayout);
        checkLayout = findViewById(R.id.checkLayout);
        numberInputSave = findViewById(R.id.numberInputSave);
        numberInput = findViewById(R.id.numberInput);
        answer = findViewById(R.id.answer);
    }

    public void saveClick(View view) {
        number = Integer.parseInt(numberInputSave.getText().toString());
        saveLayout.setVisibility(GONE);
        checkLayout.setVisibility(VISIBLE);
    }

    public void CheckClick(View view) {
        int number2 = Integer.parseInt(numberInput.getText().toString());
        if (number2 < number) {
            answer.setText("Більше!");
        } else if (number2 > number) {
            answer.setText("Менше!");
        } else {
            answer.setText("Правильно!");
        }
    }
}
