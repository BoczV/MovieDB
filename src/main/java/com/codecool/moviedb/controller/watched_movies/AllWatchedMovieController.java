package com.codecool.moviedb.controller.watched_movies;

import com.codecool.moviedb.components.MovieAPI;
import com.codecool.moviedb.repository.UserRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/all-watched-movies")
@CrossOrigin("*")
public class AllWatchedMovieController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieAPI movieAPI;

    @GetMapping
    public String getAllWatchedMovies() throws IOException, JSONException {
        return getMoviesByIDAPICall((userRepository.getOne(1L).getWatchedMovies()));
    }

    public String getMoviesByIDAPICall(Set<String> ids) throws IOException, JSONException {
        List<String> result = new ArrayList<>();
        for (String id : ids){
            result.add(movieAPI.getMovieById(id));
        }
        return result.toString();
    }
}