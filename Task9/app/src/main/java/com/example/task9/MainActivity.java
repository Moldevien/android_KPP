package com.example.task9;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText nameEditText, ageEditText;
    private SeekBar salarySeekBar;
    private TextView salaryText;
    private Button submitButton;
    private RadioButton radioButton, radioButton1, radioButton2, radioButton3, radioButton4;
    private CheckBox expCheckBox, teamCheckBox, travelCheckBox;
    private TextView resultText;

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

        nameEditText = findViewById(R.id.nameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        salarySeekBar = findViewById(R.id.salarySeekBar);
        salaryText = findViewById(R.id.salaryText);
        submitButton = findViewById(R.id.submitButton);

        radioButton = findViewById(R.id.radioButton);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);

        expCheckBox = findViewById(R.id.expCheckBox);
        teamCheckBox = findViewById(R.id.teamCheckBox);
        travelCheckBox = findViewById(R.id.travelCheckBox);
        resultText = findViewById(R.id.resultText);

        // Вивід зарплати
        salarySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                salaryText.setText("USD: " + progress);
                validateInputs();
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Валідація даних
        nameEditText.addTextChangedListener(inputWatcher);
        ageEditText.addTextChangedListener(inputWatcher);
    }

    private final TextWatcher inputWatcher = new TextWatcher() {
        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
            validateInputs();
        }
        @Override public void afterTextChanged(Editable s) {}
    };

    private void validateInputs() {

        boolean valid = !nameEditText.getText().toString().isEmpty();
        try {
            int age = Integer.parseInt(ageEditText.getText().toString());
            valid = valid && age >= 21 && age <= 40;
        } catch (NumberFormatException e) {
            valid = false;
        }

        submitButton.setEnabled(valid);
    }

    public void onClick(View view) {
        int age = Integer.parseInt(ageEditText.getText().toString());
        int salary = salarySeekBar.getProgress();
        boolean valid = age >= 21 && age <= 40 && salary >= 800 && salary <= 4000;

        if (!valid) {
            resultText.setText("Вік або зарплата не відповідають вимогам");
            resultText.setVisibility(View.VISIBLE);
            return;
        }

        int score = 0;

        // Перевірка правильних відповідей
        if (radioButton.isChecked()) score += 2;
        if (radioButton1.isChecked()) score += 2;
        if (radioButton2.isChecked()) score += 2;
        if (radioButton3.isChecked()) score += 2;
        if (radioButton4.isChecked()) score += 2;

        if (expCheckBox.isChecked()) score += 2;
        if (teamCheckBox.isChecked()) score += 1;
        if (travelCheckBox.isChecked()) score += 1;

        resultText.setVisibility(View.VISIBLE);

        if (score >= 10) {
            resultText.setText("Ви пройшли! Контакти: example@company.com");
        } else {
            resultText.setText("Недостатньо балів: " + score);
        }
    }
}