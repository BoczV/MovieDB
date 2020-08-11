package com.codecool.moviedb.controller;

import com.codecool.moviedb.components.MovieAPI;
import com.codecool.moviedb.repository.UserRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping("/recommendation")
@CrossOrigin("*")
public class SuggestedController {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    MovieAPI movieAPI;
    
    @GetMapping("/suggested/{language}")
    public String getSuggested(@PathVariable("language") String language) throws IOException, JSONException {
        Set<String> likedMovies = userRepository.getOne(1L).getLikedMovies();
        return movieAPI.getAllSuggestedMovies(likedMovies, language);
    }

    @GetMapping("/not-suggested/{language}")
    public String getNotSuggested(@PathVariable("language") String language) throws IOException, JSONException {
        Set<String> dislikedMovies = userRepository.getOne(1L).getDislikedMovies();
        return movieAPI.getAllSuggestedMovies(dislikedMovies, language);
    }
}
