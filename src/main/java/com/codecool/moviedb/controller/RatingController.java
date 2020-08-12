package com.codecool.moviedb.controller;

import com.codecool.moviedb.components.MovieAPI;
import com.codecool.moviedb.components.PostRequestSender;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URL;

@RestController
@RequestMapping("/rate-movie/{id}/{rating}")
@CrossOrigin("*")
public class RatingController {

    private String partOfPath = "https://api.themoviedb.org/3/movie/";
    private String apiKey = "/rating?api_key=ba3cb62d3d36c1bebfdd12b5074399f5&";
    private String guestSessionId = "guest_session_id=ed2701347d9d0a01d5df934ce8233be7";

    @Autowired
    PostRequestSender postRequestSender;

    @PostMapping
    public void postRating(
            @PathVariable("id") String movieId, @PathVariable("rating") String rating)
            throws IOException, JSONException {
        String path = partOfPath + movieId + apiKey + guestSessionId;
        URL url = new URL (path);
        postRequestSender.sendPostRequest(url, rating);
    }


}

