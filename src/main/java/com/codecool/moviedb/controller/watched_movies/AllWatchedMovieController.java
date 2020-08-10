package com.codecool.moviedb.controller.watched_movies;

import com.codecool.moviedb.components.GetMovieFromIDAPI;
import com.codecool.moviedb.dao.WatchedMovieDAO;
import com.codecool.moviedb.repository.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
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
    public WatchedMovieDAO watchedMovieDAO;

    @Autowired
    public GetMovieFromIDAPI getMovieFromIDAPI;

    @GetMapping
    public String getAllWatchedMovies() throws IOException, JSONException {
        return getMoviesByIDAPICall((userRepository.getOne(1L).getWatchedMovies()));
//        return getMoviesByIDAPICall(watchedMovieDAO.getAllWatchedMovieIDs());
    }

    public String getMoviesByIDAPICall(Set<String> ids) throws IOException, JSONException {
        List<JSONObject> result = new ArrayList<>();
        for (String id : ids){
            result.add(getMovieFromIDAPI.getMovieFromID(id));
        }
        System.out.println(result.toString());
        return result.toString();
    }
}