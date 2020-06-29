package com.letsEat.controller;

import com.letsEat.model.Restaurant;
import com.letsEat.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/restaurants")
    public String create(@RequestBody Restaurant r){
        Restaurant restaurant = restaurantService.create(r.getName(), r.getPricePoints(), r.getCategory());
        return restaurant.toString();
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getAll(){
        return restaurantService.getAll();
    }

    @GetMapping("/restaurants/{name}")
    public Restaurant getRestaurant(@PathVariable String name) {
        return restaurantService.getByName(name);
    }

    @GetMapping("/restaurants/category/{category}")
    public List<Restaurant> getByCategory(@PathVariable String category){
        return restaurantService.getByCategory(category);
    }

    @GetMapping("/restaurants/price-points/{pricePoints}")
    public List<Restaurant> getByPricePoints(@PathVariable String pricePoints){
        return restaurantService.getByPricePoints(pricePoints);
    }

    @PutMapping("/restaurants/{name}")
    ResponseEntity<Restaurant> update(@Valid @PathVariable String name, @RequestBody Restaurant r){
        Restaurant result = restaurantService.update(name, r.getPricePoints(), r.getCategory());
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/restaurants/{name}")
    public String delete(@PathVariable String name){
        restaurantService.delete(name);
        return "Deleted: " + name;
    }
    
//    @DeleteMapping("restaurants/deleteAll")
//    public String deleteAll(){
//        restaurantService.deleteAll();
//        return "Deleted all records.";
//    }
}