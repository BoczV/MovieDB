package com.codecool.moviedb.controller;

import com.codecool.moviedb.components.MovieAPI;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/latest-movies")
@CrossOrigin("*")
public class LatestMovieController {

    @Autowired
    MovieAPI movieAPI;

    @GetMapping("/{language}")
    public String getLatestMovies(@PathVariable("language") String language) throws IOException, JSONException {
        return movieAPI.getLatestMovies(language).toString();
    }
}
