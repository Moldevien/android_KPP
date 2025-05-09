package com.example.task10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task10.model.Car;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static ArrayList<Car> cars = new ArrayList<>();
    RecyclerView recyclerView;
    CarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cars.add(new Car("Toyota", "Camry", 2020, "Стан чудовий", 25000, R.drawable.img));
        cars.add(new Car("BMW", "X5", 2018, "Можливий торг", 35000, R.drawable.img_1));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CarAdapter(this, cars);
        recyclerView.setAdapter(adapter);
    }

    ActivityResultLauncher<Intent> searchLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    ArrayList<Car> filteredCars = (ArrayList<Car>) result.getData().getSerializableExtra("filtered");
                    if (filteredCars != null && !filteredCars.isEmpty()) {
                        recyclerView.setAdapter(new CarAdapter(this, filteredCars));
                    } else {
                        Toast.makeText(this, "Нічого не знайдено", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    public void searchClick(View view) {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        searchLauncher.launch(intent);
    }
}