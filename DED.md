# Detailed Engineering Design
## Modeling

### FoodTruck
```json
{
    id: long,
    name: String,
    type: FacilityType,
    status: Status,
    location: Location,
    foodItems: String[]
}
```

### Location
```json
{
    address: String,
    description: String,
    latitude: double,
    longitude: double
}
```

### FacilityType
```java
enum FacilityType {
    TRUCK, PUSH_CART
}
```

### Status
```java
enum Status {
    REQUESTED,
    APPROVED,
    EXPIRED,
    SUSPEND
}
```

## Subsystems
### 1. backend
Use Gradle, Java 21, Spring Boot and Spring Webflux. The backend will be responsible for handling the csv file to json. The csv will fetch from the specific official url. if it failed then it will be used the exist csv file already.
### 2. frontend
Use vue 3, vite, vue3-google-map. The frontend will use the google map and mark all the **APPROVED** Mobile Food Facilities will be shown on the San Francisco Map. Also It will depend on the local time and the dayshours. The status will depend on the Expiration Date.

## Build
Build the backend with gradle and frontend with nodejs in docker by the dockerfile. Then run them together. ** It is only for testing environment not for procduction. **

## TODO
1. The frontend now is just a demo of Google Map. It is not available for now.
2. There is no ci script or kubernetes helm chart yet.

