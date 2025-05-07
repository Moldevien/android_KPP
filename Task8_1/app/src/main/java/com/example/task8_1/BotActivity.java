package com.example.task8_1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class BotActivity extends AppCompatActivity {
    private int rnumber;
    private EditText numberInput;
    private TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot);

        rnumber = new Random().nextInt(100);
        numberInput = findViewById(R.id.numberInput);
        answer = findViewById(R.id.answer);
    }

    public void CheckClick(View view) {
        int number = Integer.parseInt(numberInput.getText().toString());
        if (number < rnumber) {
            answer.setText("Більше!");
        } else if (number > rnumber) {
            answer.setText("Менше!");
        } else {
            answer.setText("Правильно!");
        }
    }
}
