package com.codecool.moviedb.controller;

import com.codecool.moviedb.components.MovieAPI;
import com.codecool.moviedb.model.User;
import com.codecool.moviedb.repository.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping("/watchlist")
@CrossOrigin("*")
public class WatchListController {

    @Autowired
    MovieAPI movieAPI;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{language}")
    public String getWatchList(@PathVariable("language") String language) throws IOException, JSONException {
        User dummyIsti = userRepository.getOne(1L);
        Set<String> watchMovies = dummyIsti.getWatchMovies();

        StringBuilder result = new StringBuilder();
        result.append("{ 'results': [");
        for (String movieId : watchMovies) {
            result.append(movieAPI.getMovieByIdByLanguage(movieId, language)).append(",");
        }
        result.deleteCharAt(result.length() - 1);
        result.append("] }");
        JSONObject jsonResult = null;
        try {
            jsonResult = new JSONObject(String.valueOf(result));
        } catch (JSONException ex) {
            jsonResult = new JSONObject("{'results': []}");
        }

        return jsonResult.toString();
    }

    @PostMapping("/add/{movieId}")
    public void addMovieToWatchList(@PathVariable("movieId") String movieId) {
        User dummyIsti = userRepository.getOne(1L);
        dummyIsti.getWatchMovies().add(movieId);
        userRepository.save(dummyIsti);
    }

    @PostMapping("/delete/{movieId}")
    public void deleteMovieToWatchList(@PathVariable("movieId") String movieId) {
        User dummyIsti = userRepository.getOne(1L);
        dummyIsti.getWatchMovies().remove(movieId);
        userRepository.save(dummyIsti);
    }
}
