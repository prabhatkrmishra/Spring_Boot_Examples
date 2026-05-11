package com.example.MovieTicket.MovieBooking.communicator;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RatingRestCommunicator {

    @Autowired
    private RestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8081/rating";

    public long getRating(String id) {

        Long rating = restTemplate.getForObject(
                BASE_URL + "/" + id,
                Long.class
        );

        return rating;
    }

    public void addRating(Map<String, Long> ratingsMap) {

        restTemplate.postForObject(
                BASE_URL,
                ratingsMap,
                Void.class
        );

    }

    public void updateRating(Map<String, Long> ratingsMap) {

        restTemplate.put(
                BASE_URL,
                ratingsMap
        );

    }

    public void deleteRating(String id) {

        restTemplate.delete(
                BASE_URL + "/" + id
        );

    }

}