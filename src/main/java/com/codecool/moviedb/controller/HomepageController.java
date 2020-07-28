package com.codecool.moviedb.controller;

import com.codecool.moviedb.components.MovieAPI;
import com.codecool.moviedb.model.Movie;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/latest-movies")
@CrossOrigin("*")
public class HomepageController {
    @Autowired
    public MovieAPI movieAPI;

    @GetMapping
    public String getLatestMovies() throws IOException, JSONException {
        JSONObject response = movieAPI.getLatestMovies();
        return response.toString();
    }

}
