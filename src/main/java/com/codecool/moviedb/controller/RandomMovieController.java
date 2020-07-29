package com.codecool.moviedb.controller;

import com.codecool.moviedb.components.MovieAPI;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/random-movie")
@CrossOrigin("*")
public class RandomMovieController {
    @Autowired
    MovieAPI movieAPI;

    @GetMapping
    public String getRandomMovie() throws IOException, JSONException {
        JSONObject response = movieAPI.getRandomMovie();
        return response.toString();
    }
}
