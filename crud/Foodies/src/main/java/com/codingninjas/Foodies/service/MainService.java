package com.codingninjas.Foodies.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.codingninjas.Foodies.entity.Customer;
import com.codingninjas.Foodies.entity.Rating;
import com.codingninjas.Foodies.entity.Restaurant;
import com.codingninjas.Foodies.repository.CustomerRepository;
import com.codingninjas.Foodies.repository.RatingRepository;
import com.codingninjas.Foodies.repository.RestaurantRepository;

@Service
public class MainService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional
    public Rating addRating(Rating rating, Integer customerId, String restaurantName) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

        Restaurant restaurant = restaurantRepository.findByName(restaurantName);
        if (restaurant == null) {
            throw new RuntimeException("Restaurant not found with name: " + restaurantName);
        }

        rating.setCustomer(customer);
        rating.setRestaurant(restaurant);

        if (customer.getRatings() == null) {
            customer.setRatings(new java.util.ArrayList<>());
        }
        customer.getRatings().add(rating);

        if (restaurant.getRatings() == null) {
            restaurant.setRatings(new java.util.ArrayList<>());
        }
        restaurant.getRatings().add(rating);

        if (customer.getVisitedRestaurants() == null) {
            customer.setVisitedRestaurants(new java.util.ArrayList<>());
        }
        if (!customer.getVisitedRestaurants().contains(restaurant)) {
            customer.getVisitedRestaurants().add(restaurant);
        }

        Rating savedRating = ratingRepository.save(rating);

        customerRepository.save(customer);
        restaurantRepository.save(restaurant);

        return savedRating;
    }

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> getCustomersByRestaurantName(String restaurantName) {
        Restaurant restaurant = restaurantRepository.findByName(restaurantName);
        if (restaurant == null) {
            throw new RuntimeException("Restaurant not found with name: " + restaurantName);
        }
        return customerRepository.findByVisitedRestaurants(restaurant);
    }

    public List<Customer> getCustomersByRestaurantAndRatingGreaterThan(String restaurantName, double rating) {
        return customerRepository.findCustomersByRestaurantAndRatingGreaterThan(restaurantName, rating);
    }

    public double getAverageRatingForRestaurant(String restaurantName) {
        Double average = ratingRepository.getAverageRatingByRestaurantName(restaurantName);
        return average != null ? average : 0.0;
    }
}