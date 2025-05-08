package com.example.task10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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
    public static List<Car> cars = new ArrayList<>();
    private RecyclerView recyclerView;

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

        cars.add(new Car("Toyota", "Camry", 2020, "Стан чудовий", 25000, R.drawable.img));
        cars.add(new Car("BMW", "X5", 2018, "Можливий торг", 35000, R.drawable.img_1));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // адаптер для виводу
        CarAdapter adapter = new CarAdapter(this, cars);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            ArrayList<Car> filteredCars = (ArrayList<Car>) data.getSerializableExtra("filtered");
            if (filteredCars != null && !filteredCars.isEmpty()) {
                recyclerView.setAdapter(new CarAdapter(this, filteredCars));
            } else {
                Toast.makeText(this, "Нічого не знайдено", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void searchClick(View view) {
        startActivityForResult(new Intent(MainActivity.this, SearchActivity.class), 1);
    }
}