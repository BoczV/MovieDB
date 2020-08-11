package com.codecool.moviedb.controller;

import com.codecool.moviedb.components.MovieAPI;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/movie-url/{movieId}")
@CrossOrigin("*")
public class MovieURLController {

    @Autowired
    MovieAPI movieAPI;

    @GetMapping
    public String getMovieURL(@PathVariable("movieId") String movieId) throws IOException, JSONException {
        return movieAPI.getTrailersById(movieId);
    }

}
