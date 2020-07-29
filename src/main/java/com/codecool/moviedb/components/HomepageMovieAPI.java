package com.codecool.moviedb.components;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HomepageMovieAPI {

    private RemoteURLReader remoteURLReader;
    private final String apiPathLatest = "https://api.themoviedb.org/3/movie/upcoming?api_key=ba3cb62d3d36c1bebfdd12b5074399f5&language=en-US&page=1";
    private final String apiPathPopular = "https://api.themoviedb.org/3/discover/movie?api_key=ba3cb62d3d36c1bebfdd12b5074399f5&language=en&sort_by=popularity.desc";

    public HomepageMovieAPI(RemoteURLReader remoteURLReader) {
        this.remoteURLReader = remoteURLReader;
    }


    public JSONObject getLatestMovies() throws IOException, JSONException {
        String result = remoteURLReader.readFromUrl(apiPathLatest);
        JSONObject json = new JSONObject(result);
        return json;
    }

    public JSONObject getPopularMovies() throws JSONException, IOException {
        String result = remoteURLReader.readFromUrl(apiPathPopular);
        JSONObject json = new JSONObject(result);
        return json;
    }
}
