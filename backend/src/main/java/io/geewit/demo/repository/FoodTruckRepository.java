package io.geewit.demo.repository;

import io.geewit.demo.domain.FoodTruck;

import java.util.List;

public class FoodTruckRepository {

    private static final List<FoodTruck> foodTrucks;

    static {
        foodTrucks = FoodTruck.load();
    }

    public static List<FoodTruck> load() {
        return foodTrucks;
    }
}
