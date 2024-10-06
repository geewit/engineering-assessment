package io.geewit.demo.endpoint;

import io.geewit.demo.domain.FoodTruck;
import io.geewit.demo.repository.FoodTruckRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class IndexController {

    @GetMapping("/mobile-food-facilities")
    public Flux<FoodTruck> getMobileFoodTruckFacilities() {
        List<FoodTruck> footTrucks = FoodTruckRepository.load();
        return Flux.fromIterable(footTrucks);
    }
}
