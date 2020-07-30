package com.codecool.moviedb.controller;

import com.codecool.moviedb.components.SuggestedNotSuggestedAPI;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/recommendation")
@CrossOrigin("*")
public class SuggestedController {
    @Autowired
    SuggestedNotSuggestedAPI suggestedNotSuggestedAPI;

    @GetMapping("/suggested")
    public String getSuggested() throws IOException, JSONException {
        List<String> dummyList = Arrays.asList("550", "551", "552");
        String res = suggestedNotSuggestedAPI.getAllSuggestedMovies(dummyList);
        return res;
    }

    @GetMapping("/not-suggested")
    public String getNotSuggested() throws IOException, JSONException {
        List<String> dummyList = Arrays.asList("442");
        String res = suggestedNotSuggestedAPI.getAllSuggestedMovies(dummyList);
        return res;
    }
}
