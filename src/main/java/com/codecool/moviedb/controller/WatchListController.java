package com.codecool.moviedb.controller;

import com.codecool.moviedb.components.MovieAPI;
import com.codecool.moviedb.components.dao.WatchListMemDao;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/watchlist")
@CrossOrigin("*")
public class WatchListController {
    @Autowired
    WatchListMemDao watchListMemDao;
    @Autowired
    MovieAPI movieAPI;

    @GetMapping
    public String getWatchList() throws IOException, JSONException {
        List<String> watchListIds = watchListMemDao.getWatchList();
        StringBuilder result = new StringBuilder();
        result.append("{ 'results': [");
        for (String movieId: watchListIds) {
            result.append(movieAPI.getMovieById(movieId)).append(",");
        }
        result.deleteCharAt(result.length() - 1);
        result.append("] }");
        JSONObject jsonResult = new JSONObject(String.valueOf(result));
        System.out.println(watchListMemDao.getWatchList());
        System.out.println("watch list: " +jsonResult.toString());
        return  jsonResult.toString();
    }

    @GetMapping("/add/{movieId}")
    public void addMovieToWatchList(@PathVariable("movieId") String movieId) {
        System.out.println("movie id: " + movieId + " added");
        watchListMemDao.addMovieToWatchList(movieId);
    }

    @GetMapping("/delete/{movieId}")
    public void deleteMovieToWatchList(@PathVariable("movieId") String movieId) {
        System.out.println("movie id: " + movieId + " deleted");
        watchListMemDao.removeMovieFromWatchList(movieId);
    }

}
