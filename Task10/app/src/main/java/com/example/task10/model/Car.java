package com.example.task10.model;

import java.io.Serializable;

public class Car implements Serializable {
    private String brand;
    private String model;
    private int year;
    private String description;
    private int cost;
    public int imageId;

    public Car(String brand, String model, int year, String description, int cost, int imageId) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.description = description;
        this.cost = cost;
        this.imageId = imageId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    public int getImageId() {
        return imageId;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", imageId=" + imageId +
                '}';
    }
}
