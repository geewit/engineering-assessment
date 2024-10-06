package io.geewit.demo.domain;


import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class FoodTruck {
    private long id;

    private String name;

    private FacilityType type;

    private Status status;

    private Location location;

    private Set<String> foodItems;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FacilityType getType() {
        return type;
    }

    public void setType(FacilityType type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<String> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(Set<String> foodItems) {
        this.foodItems = foodItems;
    }

    public static List<FoodTruck> load() {
        String filePath = "../Mobile_Food_Facility_Permit.csv";
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            if (lines.isEmpty()) {
                return null;
            }
            List<FoodTruck> foodTrucks = new ArrayList<>();
            for (String line : lines) {
                String[] data = line.split(",");
                FoodTruck foodTruck = new FoodTruck();
                try {
                    long id = Long.parseLong(data[0]);
                    foodTruck.setId(id);
                } catch (NumberFormatException e) {
                    continue;
                }
                String name = data[1];
                foodTruck.setName(name);
                try {
                    FacilityType type = FacilityType.valueOf(data[2]);
                    foodTruck.setType(type);
                } catch (Exception e) {
                    continue;
                }
                Location location = new Location();
                String locationDescription = data[4];
                location.setDescription(locationDescription);
                String address = data[5];
                location.setAddress(address);

                try {
                    Status status = Status.valueOf(data[10]);
                    if (status != Status.APPROVED) {
                        continue;
                    }
                    foodTruck.setStatus(status);
                } catch (Exception e) {
                    continue;
                }

                try {
                    BigDecimal latitude = BigDecimal.valueOf(Double.parseDouble(data[14]));
                    location.setLatitude(latitude);
                } catch (Exception e) {
                    continue;
                }

                try {
                    BigDecimal longitude = BigDecimal.valueOf(Double.parseDouble(data[15]));
                    location.setLongitude(longitude);
                } catch (Exception e) {
                    continue;
                }
                foodTruck.setLocation(location);

                foodTruck.setFoodItems(Arrays.stream(data[11].split(":")).filter(String::isBlank).map(String::trim).collect(Collectors.toSet()));

                foodTrucks.add(foodTruck);
            }
            return foodTrucks;
        } catch (IOException e) {
            return null;
        }

    }
}
