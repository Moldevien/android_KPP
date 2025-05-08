package com.example.task10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task10.model.Car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SearchActivity extends AppCompatActivity {
    private AutoCompleteTextView brandInput, modelInput;
    private Spinner spinnerYearFrom, spinnerYearTo, spinnerPriceFrom, spinnerPriceTo;

    private List<Car> cars = MainActivity.cars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        brandInput = findViewById(R.id.brandInput);
        modelInput = findViewById(R.id.modelInput);
        spinnerYearFrom = findViewById(R.id.spinnerYearFrom);
        spinnerYearTo = findViewById(R.id.spinnerYearTo);
        spinnerPriceFrom = findViewById(R.id.spinnerPriceFrom);
        spinnerPriceTo = findViewById(R.id.spinnerPriceTo);

        // автозаповнення марок та моделей
        Set<String> brands = new HashSet<>();
        Set<String> models = new HashSet<>();
        Set<Integer> years = new TreeSet<>();
        List<Integer> prices = Arrays.asList(1000, 5000, 10000, 20000, 50000, 100000);
        for (Car c : cars) {
            brands.add(c.getBrand());
            models.add(c.getModel());
            years.add(c.getYear());
        }

        ArrayAdapter<String> brandAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, new ArrayList<>(brands));
        brandInput.setAdapter(brandAdapter);

        ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, new ArrayList<>(models));
        modelInput.setAdapter(modelAdapter);

        ArrayAdapter<Integer> yearAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, new ArrayList<>(years));
        spinnerYearFrom.setAdapter(yearAdapter);
        spinnerYearTo.setAdapter(yearAdapter);

        ArrayAdapter<Integer> priceAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, prices);
        spinnerPriceFrom.setAdapter(priceAdapter);
        spinnerPriceTo.setAdapter(priceAdapter);
    }

    public void searchClick(View view) {
        String brand = brandInput.getText().toString().trim().toLowerCase();
        String model = modelInput.getText().toString().trim().toLowerCase();

        int yearFrom = (int) spinnerYearFrom.getSelectedItem();
        int yearTo = (int) spinnerYearTo.getSelectedItem();

        if (yearFrom > yearTo) {
            Toast.makeText(this, "Рік 'від' не може бути більше ніж 'до'", Toast.LENGTH_SHORT).show();
            return;
        }

        int priceFrom = (int) spinnerPriceFrom.getSelectedItem();
        int priceTo = (int) spinnerPriceTo.getSelectedItem();

        if (priceFrom > priceTo) {
            Toast.makeText(this, "Ціна 'від' не може бути більше ніж 'до'", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!(!brand.isEmpty() || !model.isEmpty()) &&
                yearFrom == yearTo &&
                priceFrom == priceTo) {
            Toast.makeText(this, "Заповніть хоча б одне поле для пошуку", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<Car> filtered = new ArrayList<>();

        for (Car car : cars) {
            boolean matches = true;

            if (!brand.isEmpty() && !car.getBrand().toLowerCase().contains(brand)) matches = false;
            if (!model.isEmpty() && !car.getModel().toLowerCase().contains(model)) matches = false;
            if (car.getYear() < yearFrom || car.getYear() > yearTo) matches = false;
            if (car.getCost() < priceFrom || car.getCost() > priceTo) matches = false;

            if (matches) filtered.add(car);
        }

        if (filtered.isEmpty()) {
            Toast.makeText(this, "Нічого не знайдено", Toast.LENGTH_SHORT).show();
        } else {
            Intent result = new Intent();
            result.putExtra("filtered", filtered);
            setResult(RESULT_OK, result);
            finish();
        }
    }
}
