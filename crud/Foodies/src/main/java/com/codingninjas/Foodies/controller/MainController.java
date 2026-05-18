package com.codingninjas.Foodies.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.codingninjas.Foodies.entity.Customer;
import com.codingninjas.Foodies.entity.Rating;
import com.codingninjas.Foodies.entity.Restaurant;
import com.codingninjas.Foodies.service.MainService;

@RestController
public class MainController {

    @Autowired
    private MainService service;

    @PostMapping("/Restaurant/add")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return service.addRestaurant(restaurant);
    }

    @PostMapping("/Customer/add")
    public Customer addCustomer(@RequestBody Customer customer) {
        return service.addCustomer(customer);
    }

    @PostMapping("/Rating/{customerId}/add/{restaurantName}")
    public Rating addRating(@RequestBody Rating rating,
                            @PathVariable Integer customerId,
                            @PathVariable String restaurantName) {
        return service.addRating(rating, customerId, restaurantName);
    }

    @GetMapping("/ratings")
    public List<Rating> getAllRatings() {
        return service.getAllRatings();
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }

    @GetMapping("/customers/restaurant/{restaurantName}")
    public List<Customer> getCustomersByRestaurant(@PathVariable String restaurantName) {
        return service.getCustomersByRestaurantName(restaurantName);
    }

    @GetMapping("/customers/restaurant/{restaurantName}/{rating}")
    public List<Customer> getCustomersByRestaurantAndRatingGreaterThan(@PathVariable String restaurantName, @PathVariable double rating) {
        return service.getCustomersByRestaurantAndRatingGreaterThan(restaurantName, rating);
    }

    @GetMapping("/restaurant/{restaurantName}/average")
    public double getAverageRatingForRestaurant(@PathVariable String restaurantName) {
        return service.getAverageRatingForRestaurant(restaurantName);
    }
}