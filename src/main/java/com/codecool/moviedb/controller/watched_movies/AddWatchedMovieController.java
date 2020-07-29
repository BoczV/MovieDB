package com.codecool.moviedb.controller.watched_movies;

import com.codecool.moviedb.dao.WatchedMovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/add/watched-movie/{id}")
@CrossOrigin("*")
public class AddWatchedMovieController {

    @Autowired
    WatchedMovieDAO watchedMovieDAO;

    @GetMapping
    public void addWatchedMovie(@PathVariable String id) {
        watchedMovieDAO.addMovie(id);
        System.out.println("watched list: " + watchedMovieDAO.getAllWatchedMovieIDs());
    }
}
