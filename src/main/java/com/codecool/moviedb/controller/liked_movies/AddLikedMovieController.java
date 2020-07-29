package com.codecool.moviedb.controller.liked_movies;

import com.codecool.moviedb.dao.LikedMovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/add/liked-movie/{id}")
@CrossOrigin("*")
public class AddLikedMovieController {

    @Autowired
    LikedMovieDAO likedMovieDAO;

    @GetMapping
    public void addLikedMovie(@PathVariable String id) {
        likedMovieDAO.addMovie(id);
        System.out.println(likedMovieDAO.getAllWatchedMovieIDs());
    }
}

