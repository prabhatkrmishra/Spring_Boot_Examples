package com.example.MovieTicket.MovieBooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.MovieTicket.MovieBooking.Model.Movie;
import com.example.MovieTicket.MovieBooking.service.MovieServiceInterface;

import jakarta.validation.Valid;

@RestController
public class controller {

    @Autowired
    private MovieServiceInterface movieService;

    @GetMapping("/ticket/movies")
    public List<Movie> getAllMovies() {

        return movieService.getAllMovies();

    }

    @PostMapping("/ticket/movie")
    public void addMovie(@Valid @RequestBody Movie movie,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RuntimeException("Invalid Movie");
        }

        movieService.addMovie(movie);

    }

    @GetMapping("/ticket/movie/{id}")
    public Movie getMovieById(@PathVariable String id) {

        return movieService.getMovieById(id);

    }

    @DeleteMapping("/ticket/movie/{id}")
    public void deleteMovie(@PathVariable String id) {

        movieService.deleteMovieById(id);

    }

    @PutMapping("/ticket/update/{id}")
    public void updateMovie(@Valid @RequestBody Movie movie,
                            @PathVariable String id) {

        movieService.updateMovie(movie, id);

    }

}