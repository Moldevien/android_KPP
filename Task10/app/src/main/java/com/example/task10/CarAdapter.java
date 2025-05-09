package com.example.task10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task10.model.Car;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {
    private List<Car> cars;
    private LayoutInflater inflater;
    //private OnItemClickListener listener;

    public CarAdapter(Context context, List<Car> cars) {
        this.cars = cars;
        this.inflater = LayoutInflater.from(context);
    }

    /*public interface OnItemClickListener {
        void onItemClick(Car car);
    }*/

    /*public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }*/

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = cars.get(position);
        holder.imageView.setImageResource(car.getImageId());
        holder.textBrand.setText(car.getBrand());
        holder.textModel.setText(car.getModel());
        holder.textYear.setText(String.valueOf(car.getYear()));
        holder.textCost.setText(String.valueOf(car.getCost()));
        holder.textDescription.setText(car.getDescription());

        /*holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(car);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    static class CarViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView textBrand, textModel, textYear, textCost, textDescription;

        public CarViewHolder(View view) {
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
