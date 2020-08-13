package com.codecool.moviedb.controller;

import com.codecool.moviedb.components.MovieAPI;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/genre-search")
@CrossOrigin("*")
public class GenreController {

    @Autowired
    MovieAPI movieAPI;

    //a page number kimaradt a queryből!
    @GetMapping("/{genreId}/{page}/{language}")
    public String getMoviesByGenre(@PathVariable("genreId") String genreId, @PathVariable("page") String page, @PathVariable("language") String language) throws IOException, JSONException {
        String result = movieAPI.getMoviesByGenre(genreId, page, language);
        System.out.println(result);
        return result;
    }
}
