package com.codecool.moviedb.controller;

import com.codecool.moviedb.chat.MessageBean;
import com.codecool.moviedb.components.MovieAPI;
import com.codecool.moviedb.repository.MessageBeanRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movie")
@CrossOrigin("*")
public class DetailedMovieController {
    @Autowired
    MovieAPI movieAPI;

    @GetMapping("/{movieId}/{language}")
    public String getMovieById(@PathVariable("movieId") String movieId, @PathVariable("language") String language) throws IOException, JSONException {
        String result = movieAPI.getMovieByIdByLanguage(movieId, language);
        JSONObject jsonObject = new JSONObject(result);
        return jsonObject.toString();
    }
}
