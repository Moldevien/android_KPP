package com.example.task10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.task10.model.Car;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<Car> cars;

    public CarAdapter(Context context, List<Car> cars) {
        this.cars = cars;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public CarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_car, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CarAdapter.ViewHolder holder, int position) {
        Car car = cars.get(position);
        holder.textBrand.setText(car.getBrand());
        holder.textModel.setText(car.getModel());
        holder.textYear.setText(car.getYear());
        holder.textCost.setText(car.getCost());
        holder.textDescription.setText(car.getDescription());
        holder.imageView.setImageResource(car.getImageId());
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textBrand, textModel, textYear, textCost, textDescription;
        final ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            textBrand = view.findViewById(R.id.textBrand);
            textModel = view.findViewById(R.id.textModel);
            textYear = view.findViewById(R.id.textYear);
            textCost = view.findViewById(R.id.textCost);
            textDescription = view.findViewById(R.id.textDescription);
            imageView = view.findViewById(R.id.imageView);
        }
    }
}
