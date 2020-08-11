package com.codecool.moviedb.components;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Set;

@Component
public class MovieAPI {


    private RemoteURLReader remoteURLReader;
    private final String apiPathLatest = "https://api.themoviedb.org/3/movie/upcoming?api_key=ba3cb62d3d36c1bebfdd12b5074399f5&language=en-US&page=1";
    private final String apiPathPopular = "https://api.themoviedb.org/3/discover/movie?api_key=ba3cb62d3d36c1bebfdd12b5074399f5&language=en&sort_by=popularity.desc";

    public MovieAPI(RemoteURLReader remoteURLReader) {
        this.remoteURLReader = remoteURLReader;
    }

    public JSONObject searchResults(String searchString, String language) throws JSONException, IOException {
        searchString = searchString.replaceAll(" ", "+");
        String queryPath = "https://api.themoviedb.org/3/search/movie?api_key=ba3cb62d3d36c1bebfdd12b5074399f5&query="
                + searchString + "&language=" + language;
        String result = remoteURLReader.readFromUrl(queryPath);
        return new JSONObject(result);
    }

    public JSONObject getRandomMovie(String language) throws IOException, JSONException {
        String result = getRandomPath(language);
        return new JSONObject(result);
    }

    public String getRandomPath(String language) throws IOException {
        String result = "";
        try {
            Random random = new Random();
            int randomId = 1 + random.nextInt(990);
            String path = "https://api.themoviedb.org/3/movie/" + randomId + "?api_key=ba3cb62d3d36c1bebfdd12b5074399f5&language=" + language;
            result = remoteURLReader.readFromUrl(path);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            result = getRandomPath(language);
        }
        return result;
    }

    public String getMovieById(String movieId) throws IOException {
        String path = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=ba3cb62d3d36c1bebfdd12b5074399f5&" +
                "append_to_response=credits";
        return remoteURLReader.readFromUrl(path);
    }

    public String getMovieByIdByLanguage(String movieId, String language) throws IOException {
        String path = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=ba3cb62d3d36c1bebfdd12b5074399f5&" +
                "append_to_response=credits&language=" + language;
        return remoteURLReader.readFromUrl(path);
    }

    public JSONObject getLatestMovies(String language) throws IOException, JSONException {
        String result = remoteURLReader.readFromUrl(apiPathLatest + "&language=" + language);
        JSONObject json = new JSONObject(result);
        return json;
    }

    public JSONObject getPopularMovies(String language) throws JSONException, IOException {
        String result = remoteURLReader.readFromUrl(apiPathPopular + "&language=" + language);
        JSONObject json = new JSONObject(result);
        return json;
    }

    public String getAllSuggestedMovies(Set<String> movieList, String language) throws JSONException, IOException {
        StringBuilder result = new StringBuilder();
        result.append("{ 'results': [");

        for (String movieId : movieList) {
            JSONArray jsonArray = getSuggestedByMovie(movieId, language);
            if(jsonArray.length() > 3){
                for (int i=0; i < 3; i++) {
                    result.append(jsonArray.optJSONObject(i).toString()).append(",");
                }
            } else {
                for (int i=0; i < jsonArray.length(); i++) {
                    result.append(jsonArray.optJSONObject(i).toString()).append(",");
                }
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

    public JSONArray getSuggestedByMovie(String movieId, String language) throws JSONException, IOException {
        String url = "https://api.themoviedb.org/3/movie/" + movieId + "/recommendations?api_key=ba3cb62d3d36c1bebfdd12b5074399f5&language=en-US&page=1&language=" + language;
        String result = remoteURLReader.readFromUrl(url);
        JSONObject jsonResult = new JSONObject(result);
        return jsonResult.optJSONArray("results");
    }


    public String getTrailersById(String movieId) throws IOException, JSONException {
        String url = "https://api.themoviedb.org/3/movie/" + movieId + "/videos?api_key=ba3cb62d3d36c1bebfdd12b5074399f5";
        String result = remoteURLReader.readFromUrl(url);
        JSONObject jsonObject = new JSONObject(result);
        return jsonObject.toString();
    }
}