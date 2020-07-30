package com.codecool.moviedb.controller;

import com.codecool.moviedb.components.SuggestedNotSuggestedAPI;
import com.codecool.moviedb.dao.DislikedMovieDAO;
import com.codecool.moviedb.dao.LikedMovieDAO;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/recommendation")
@CrossOrigin("*")
public class SuggestedController {
    @Autowired
    SuggestedNotSuggestedAPI suggestedNotSuggestedAPI;
    @Autowired
    LikedMovieDAO likedMovieDAO;
    @Autowired
    DislikedMovieDAO dislikedMovieDAO;

    @GetMapping("/suggested")
    public String getSuggested() throws IOException, JSONException {
        Set<String> likedMovies = likedMovieDAO.getAllLikedMovies();
        String res = suggestedNotSuggestedAPI.getAllSuggestedMovies(likedMovies);
        return res;
    }

    @GetMapping("/not-suggested")
    public String getNotSuggested() throws IOException, JSONException {
        Set<String> dislikedMovies = dislikedMovieDAO.getAllDislikedMovies();
        String res = suggestedNotSuggestedAPI.getAllSuggestedMovies(dislikedMovies);
        return res;
    }
}
