package com.codecool.moviedb.controller.liked_movies;

import com.codecool.moviedb.components.GetMovieFromIDAPI;
import com.codecool.moviedb.dao.LikedMovieDAO;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping("/all-liked-movies")
@CrossOrigin("*")
public class AllLikedMovieController {

    @Autowired
    LikedMovieDAO likedMovieDAO;

    @Autowired
    GetMovieFromIDAPI getMovieFromIDAPI;

    @GetMapping
    public String getAllLikedMovie() throws IOException, JSONException {
        return getMoviesByIDAPICall(likedMovieDAO.getAllWatchedMovieIDs());
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

