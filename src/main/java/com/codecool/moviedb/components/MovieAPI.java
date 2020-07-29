package com.codecool.moviedb.components;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

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

    public JSONObject searchResults(String searchString) throws JSONException, IOException {
        searchString = searchString.replaceAll(" ", "+");
        String queryPath = "https://api.themoviedb.org/3/search/movie?api_key=ba3cb62d3d36c1bebfdd12b5074399f5&query="
                + searchString;
        String result = remoteURLReader.readFromUrl(queryPath);
        return new JSONObject(result);
    }

    public JSONObject getRandomMovie() throws IOException, JSONException {
        String result = getRandomPath();
        return new JSONObject(result);
    }

    public String getRandomPath() throws IOException {
        String result = "";
        try {
            Random random = new Random();
            int randomId = 1 + random.nextInt(990);
            String path = "https://api.themoviedb.org/3/movie/" + randomId + "?api_key=ba3cb62d3d36c1bebfdd12b5074399f5";
            result = remoteURLReader.readFromUrl(path);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            result = getRandomPath();
        }
        return result;
    }

    public String getMovieById(String movieId) throws IOException {
        String path = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=ba3cb62d3d36c1bebfdd12b5074399f5";

        return remoteURLReader.readFromUrl(path);
    }
}
