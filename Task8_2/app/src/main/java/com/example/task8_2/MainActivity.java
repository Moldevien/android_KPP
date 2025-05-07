package com.example.task8_2;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView textInput;
    String current = "";
    double result = 0;
    String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textInput = findViewById(R.id.textInput);
        ViewGroup layout = findViewById(R.id.gridLayout);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View v = layout.getChildAt(i);
            if (v instanceof Button) {
                v.setOnClickListener(this::onClick);
            }
        }
    }

    public void onClick(View v) {
        String text = ((Button) v).getText().toString();

        switch (text) {
            case "=":
                calculate();
                break;
            case "+":
            case "-":
            case "×":
            case "÷":
                operator = text;
                result = Double.parseDouble(current);
                current = "";
                break;
            case "C":
                current = "";
                result = 0;
                operator = "";
                textInput.setText("0");
                break;
            default:
                current += text;
                textInput.setText(current);
        }
    }

    private void calculate() {
        double second = Double.parseDouble(current);
        switch (operator) {
            case "+": result += second; break;
            case "-": result -= second; break;
            case "×": result *= second; break;
            case "÷": result /= second; break;
        }
        textInput.setText(String.valueOf(result));
        current = "";
    }
}