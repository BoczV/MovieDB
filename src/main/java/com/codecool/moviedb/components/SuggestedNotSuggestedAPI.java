package com.codecool.moviedb.components;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class SuggestedNotSuggestedAPI {

    @Autowired
    private RemoteURLReader remoteURLReader;

    public String getAllSuggestedMovies(List<String> movieList) throws JSONException, IOException {
        StringBuilder result = new StringBuilder();
        result.append("{ 'results': [");

        for (String movieId : movieList) {
            JSONArray jsonArray = getSuggestedByMovie(movieId);
            for (int i=0; i <3; i++) {
                result.append(jsonArray.optJSONObject(i).toString()).append(",");
            }

        }
        result.deleteCharAt(result.length() - 1);
        result.append("] }");

        JSONObject jsonResult = null;
        try {
            jsonResult = new JSONObject(String.valueOf(result));
        } catch (JSONException ex) {
            jsonResult = new JSONObject("{'results': []}");
        }

        return  jsonResult.toString();
    }

    public JSONArray getSuggestedByMovie(String movieId) throws JSONException, IOException {
        String url = "https://api.themoviedb.org/3/movie/" + movieId + "/recommendations?api_key=ba3cb62d3d36c1bebfdd12b5074399f5&language=en-US&page=1";
        String result = remoteURLReader.readFromUrl(url);
        JSONObject jsonResult = new JSONObject(result);
        return jsonResult.optJSONArray("results");
    }

}
