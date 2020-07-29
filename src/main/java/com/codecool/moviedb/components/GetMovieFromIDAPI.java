package com.codecool.moviedb.components;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GetMovieFromIDAPI {

    private RemoteURLReader remoteURLReader;

    public GetMovieFromIDAPI(RemoteURLReader remoteURLReader) {
        this.remoteURLReader = remoteURLReader;
    }

    public JSONObject getMovieFromID(String ID) throws IOException, JSONException {
        String url = String.format("https://api.themoviedb.org/3/movie/%s?api_key=ba3cb62d3d36c1bebfdd12b5074399f5", ID);
        String result = remoteURLReader.readFromUrl(url);
        JSONObject json = new JSONObject(result);
        return json;
    }

}
