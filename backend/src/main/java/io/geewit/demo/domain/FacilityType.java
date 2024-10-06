package io.geewit.demo.domain;

public enum FacilityType {

    TRUCK("Truck"), PUSH_CART("Push Cart"), UNKNOWN("Unknown");

    final String name;

    FacilityType(String name) {
        this.name = name;
    }
}
