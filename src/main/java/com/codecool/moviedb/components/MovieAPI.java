package com.codecool.moviedb.components;

import com.codecool.moviedb.model.Movie;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Component
public class MovieAPI {

    private RemoteURLReader remoteURLReader;
    private final String apiPath = "https://api.themoviedb.org/3/movie/upcoming?api_key=ba3cb62d3d36c1bebfdd12b5074399f5&language=en-US&page=1";

    public MovieAPI(RemoteURLReader remoteURLReader) {
        this.remoteURLReader = remoteURLReader;
    }


    public JSONObject getLatestMovies() throws IOException, JSONException {
        String result = remoteURLReader.readFromUrl(apiPath);
        JSONObject json = new JSONObject(result);
        return json;
    }

}
