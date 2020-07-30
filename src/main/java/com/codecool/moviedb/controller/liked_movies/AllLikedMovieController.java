package com.codecool.moviedb.controller.liked_movies;

import com.codecool.moviedb.components.GetMovieFromIDAPI;
import com.codecool.moviedb.dao.LikedMovieDAO;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        return getMoviesByIDAPICall(likedMovieDAO.getAllLikedMovies());
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

