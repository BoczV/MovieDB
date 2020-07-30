package com.codecool.moviedb.controller;

import com.codecool.moviedb.components.MovieAPI;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/movie")
@CrossOrigin("*")
public class DetailedMovieController {
    @Autowired
    MovieAPI movieAPI;

    @GetMapping("/{movieId}")
    public String getMovieById(@PathVariable("movieId") String movieId) throws IOException, JSONException {
        String result = movieAPI.getMovieById(movieId);
        JSONObject jsonObject = new JSONObject(result);
        System.out.println(result);
        return jsonObject.toString();
    }
}
