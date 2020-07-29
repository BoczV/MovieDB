package com.codecool.moviedb.controller;

import com.codecool.moviedb.components.GetMovieFromIDAPI;
import com.codecool.moviedb.dao.WatchedMovieDAO;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping("/all-watched-movies")
@CrossOrigin("*")
public class AllWatchedMovieController {

    @Autowired
    public WatchedMovieDAO watchedMovieDAO;

    @Autowired
    public GetMovieFromIDAPI getMovieFromIDAPI;

    @GetMapping
    public String getAllWatchedMovies() throws IOException, JSONException {
        return getMoviesByIDAPICall(watchedMovieDAO.getAllWatchedMovieIDs());
    }

    public String getMoviesByIDAPICall(Set<String> ids) throws IOException, JSONException {
        StringBuilder result = new StringBuilder();
        for (String id : ids){
            result.append(getMovieFromIDAPI.getMovieFromID(id));
        }
        System.out.println(result.toString());
        return result.toString();
    }
}