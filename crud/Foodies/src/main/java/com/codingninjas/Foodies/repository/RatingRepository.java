package com.codingninjas.Foodies.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.codingninjas.Foodies.entity.Customer;
import com.codingninjas.Foodies.entity.Restaurant;
import com.codingninjas.Foodies.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

    @Query(value = "SELECT AVG(r.rating) FROM rating r JOIN restaurant res ON r.restaurant_id = res.id WHERE res.name = :restaurantName", nativeQuery = true)
    Double getAverageRatingByRestaurantName(@Param("restaurantName") String restaurantName);
}