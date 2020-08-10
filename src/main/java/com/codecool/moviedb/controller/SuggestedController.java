package com.codecool.moviedb.controller;

import com.codecool.moviedb.components.SuggestedNotSuggestedAPI;
import com.codecool.moviedb.repository.UserRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping("/recommendation")
@CrossOrigin("*")
public class SuggestedController {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    SuggestedNotSuggestedAPI suggestedNotSuggestedAPI;
    
    @GetMapping("/suggested")
    public String getSuggested() throws IOException, JSONException {
        Set<String> likedMovies = userRepository.getOne(1L).getLikedMovies();
        return suggestedNotSuggestedAPI.getAllSuggestedMovies(likedMovies);
    }

    @GetMapping("/not-suggested")
    public String getNotSuggested() throws IOException, JSONException {
        Set<String> dislikedMovies = userRepository.getOne(1L).getDislikedMovies();
        return suggestedNotSuggestedAPI.getAllSuggestedMovies(dislikedMovies);
    }
}
