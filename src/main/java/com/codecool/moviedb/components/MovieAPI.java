package com.codecool.moviedb.components;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
public class MovieAPI {

    private APIDataProvider apiDataProvider;
    private RemoteURLReader remoteURLReader;
    private String apiKey;
    private String guestSession;
    private final String apiPathLatest;
    private final String apiPathPopular;

    public MovieAPI(RemoteURLReader remoteURLReader, APIDataProvider apiDataProvider) {
        this.apiDataProvider = apiDataProvider;
        this.remoteURLReader = remoteURLReader;
        setApiKey();
        setGuestSession();
        apiPathLatest = "https://api.themoviedb.org/3/movie/upcoming?api_key=" + apiKey + "&language=en-US&page=1";
        apiPathPopular = "https://api.themoviedb.org/3/discover/movie?api_key=" + apiKey + "&language=en&sort_by=popularity.desc";
    }
    
    private void setApiKey(){
        apiKey = apiDataProvider.getPreciseKey(KeyType.API_KEY);
    }
    
    private void setGuestSession(){
        guestSession = apiDataProvider.getPreciseKey(KeyType.GUEST_SESSION);
    }

    public JSONObject searchResults(String searchString, String language) throws JSONException, IOException {
        searchString = searchString.replaceAll(" ", "+");
        String queryPath = "https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query="
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
            String path = "https://api.themoviedb.org/3/movie/" + randomId + "?api_key=" + apiKey + "&language=" + language;
            result = remoteURLReader.readFromUrl(path);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            result = getRandomPath(language);
        }
        return result;
    }

    public String getMovieById(String movieId) throws IOException {
        String path = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey + "&" +
                "append_to_response=credits";
        return remoteURLReader.readFromUrl(path);
    }

    public String getMovieByIdByLanguage(String movieId, String language) throws IOException {
        String path = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey + "&" +
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

    public JSONArray getSuggestedByMovie(String movieId, String language) throws JSONException, IOException {
        String url = "https://api.themoviedb.org/3/movie/" + movieId + "/recommendations?api_key=" + apiKey +"&language=en-US&page=1&language=" + language;
        String result = remoteURLReader.readFromUrl(url);
        JSONObject jsonResult = new JSONObject(result);
        return jsonResult.optJSONArray("results");
    }


    public String getTrailersById(String movieId) throws IOException, JSONException {
        String url = "https://api.themoviedb.org/3/movie/" + movieId + "/videos?api_key=" + apiKey;
        String result = remoteURLReader.readFromUrl(url);
        JSONObject jsonObject = new JSONObject(result);
        return jsonObject.toString();
    }

    public String getMoviesByGenre(String genreId, String page, String language) throws IOException, JSONException {
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + apiKey + "&language= "
                + language + "&sort_by=popularity.desc&include_adult=false&include_video=false&page=" + page + "&with_genres="
                + genreId;
        String result = remoteURLReader.readFromUrl(url);
        JSONObject jsonObject = new JSONObject(result);
        return jsonObject.toString();
    }


    public String getAllSuggestedMovies(Set<String> movieList, String language) throws JSONException, IOException {
        String result = requestBodyBuilder(movieList, language);
        return returnJsonObject(result).toString();
    }

    private JSONObject returnJsonObject(String result) throws JSONException {
        JSONObject jsonResult = null;
        try {
            jsonResult = new JSONObject(result);
        } catch (JSONException ex) {
            jsonResult = new JSONObject("{'results': []}");
        }
        return jsonResult;
    }

    private void iterateAndValidateJsonElement(JSONArray jsonArray, Set<String> movieIds, StringBuilder result, int numberOfElements) throws JSONException {
        for (int i = 0; i < numberOfElements; i++) {
            if (movieIds.add(jsonArray.optJSONObject(i).get("id").toString())) {
                result.append(jsonArray.optJSONObject(i).toString()).append(",");
            }
        }
    }

    private String requestBodyBuilder(Set<String> movieList, String language) throws IOException, JSONException {
        StringBuilder result = new StringBuilder();
        Set<String> movieIds = new HashSet<>();
        result.append("{ 'results': [");
        int maxSuggestionOfAMovie = 3;

        for (String movieId : movieList) {
            JSONArray jsonArray = getSuggestedByMovie(movieId, language);
            if (jsonArray.length() > maxSuggestionOfAMovie) {
                iterateAndValidateJsonElement(jsonArray, movieIds, result, maxSuggestionOfAMovie);
            } else {
                iterateAndValidateJsonElement(jsonArray, movieIds, result, jsonArray.length());
            }
        }
        result.deleteCharAt(result.length() - 1);
        result.append("] }");
        return String.valueOf(result);
    }
}
