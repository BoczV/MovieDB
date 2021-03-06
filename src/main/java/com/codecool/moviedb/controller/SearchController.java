package com.codecool.moviedb.controller;

import com.codecool.moviedb.components.MovieAPI;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/search-result")
@CrossOrigin("*")
public class SearchController {

    @Autowired
    private MovieAPI movieAPI;

    @GetMapping("/{searchString}")
    public String searchResults(@PathVariable("searchString") String searchString) throws IOException, JSONException {
        JSONObject result = movieAPI.searchResults(searchString);
        return result.toString();
    }



}
