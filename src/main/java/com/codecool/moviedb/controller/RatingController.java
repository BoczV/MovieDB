package com.codecool.moviedb.controller;

import com.codecool.moviedb.components.APIDataProvider;
import com.codecool.moviedb.components.KeyType;
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

    @Autowired
    PostRequestSender postRequestSender;

    @Autowired
    APIDataProvider apiDataProvider;

    private String partOfPath = "https://api.themoviedb.org/3/movie/";
    private String apiKey = "/rating?api_key=";
    private String guestSessionId = "guest_session_id=";

    @PostMapping
    public void postRating(
            @PathVariable("id") String movieId, @PathVariable("rating") String rating)
            throws IOException, JSONException {
        apiKey += apiDataProvider.getPreciseKey(KeyType.API_KEY) + "&";
        guestSessionId += apiDataProvider.getPreciseKey(KeyType.GUEST_SESSION);

        String path = partOfPath + movieId + apiKey + guestSessionId;
        URL url = new URL (path);
        postRequestSender.sendPostRequest(url, rating);
    }
}
