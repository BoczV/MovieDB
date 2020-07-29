package com.codecool.moviedb.controller;

import com.codecool.moviedb.dao.WatchedMovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delete/watched-movie/{id}")
@CrossOrigin("*")
public class DeleteWatchedMovieController {

    @Autowired
    WatchedMovieDAO watchedMovieDAO;

    @GetMapping
    public void addWatchedMovie(@PathVariable String id) {
        watchedMovieDAO.deleteMovie(id);
        System.out.println(watchedMovieDAO.getAllWatchedMovieIDs());
    }
}
