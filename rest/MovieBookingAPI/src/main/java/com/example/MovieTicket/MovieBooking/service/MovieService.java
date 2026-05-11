package com.example.MovieTicket.MovieBooking.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MovieTicket.MovieBooking.Exceptions.IdAlreadyExist;
import com.example.MovieTicket.MovieBooking.Exceptions.IdNotFound;
import com.example.MovieTicket.MovieBooking.Model.Movie;
import com.example.MovieTicket.MovieBooking.communicator.RatingRestCommunicator;

@Service
public class MovieService implements MovieServiceInterface {

    List<Movie> movies = new ArrayList<>();

    @Autowired
    private RatingRestCommunicator communicator;

    @Override
    public List<Movie> getAllMovies() {

        for (Movie movie : movies) {

            long rating = communicator.getRating(movie.getId());

            movie.setMovieRating(rating);
        }

        return movies;
    }

    @Override
    public void addMovie(Movie movie) {

        for (Movie m : movies) {

            if (m.getId().equals(movie.getId())) {
                throw new IdAlreadyExist("Id already exists");
            }

        }

        movies.add(movie);

        Map<String, Long> map = new HashMap<>();

        map.put(movie.getId(), movie.getMovieRating());

        communicator.addRating(map);

    }

    @Override
    public Movie getMovieById(String id) {

        for (Movie movie : movies) {

            if (movie.getId().equals(id)) {

                long rating = communicator.getRating(id);

                movie.setMovieRating(rating);

                return movie;
            }

        }

        throw new IdNotFound("Movie not found");

    }

    @Override
    public void deleteMovieById(String id) {

        Movie movie = getMovieById(id);

        movies.remove(movie);

        communicator.deleteRating(id);

    }

    @Override
    public void deleteMovie(String id) {
        deleteMovieById(id);
    }

    @Override
    public void updateMovie(Movie movie, String id) {

        Movie existingMovie = getMovieById(id);

        movies.remove(existingMovie);

        movies.add(movie);

        Map<String, Long> map = new HashMap<>();

        map.put(movie.getId(), movie.getMovieRating());

        communicator.updateRating(map);

    }

}