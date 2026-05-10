package com.example.MovieTicket.MovieBooking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.MovieTicket.MovieBooking.Exceptions.IdAlreadyExist;
import com.example.MovieTicket.MovieBooking.Exceptions.IdNotFound;
import com.example.MovieTicket.MovieBooking.Model.Movie;

@Service
public class MovieService implements MovieServiceInterface {

    List<Movie> movies = new ArrayList<>();

    @Override
    public List<Movie> getAllMovies() {
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
    }

    @Override
    public Movie getMovieById(String id) {

        for (Movie m : movies) {
            if (m.getId().equals(id)) {
                return m;
            }
        }

        throw new IdNotFound("Movie not found");
    }

    @Override
    public void deleteMovieById(String id) {

        Movie movie = getMovieById(id);

        movies.remove(movie);

    }

    @Override
    public void updateMovie(Movie movie, String id) {

        Movie existingMovie = getMovieById(id);

        movies.remove(existingMovie);

        movies.add(movie);

    }

}