package com.codecool.moviedb.controller;

import com.codecool.moviedb.components.MovieAPI;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/random-movie")
@CrossOrigin("*")
public class RandomMovieController {
    @Autowired
    MovieAPI movieAPI;

    @GetMapping("/{language}")
    public String getRandomMovie(@PathVariable("language") String language) throws IOException, JSONException {
        JSONObject response = movieAPI.getRandomMovie(language);
        return response.toString();
    }
}
