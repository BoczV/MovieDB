package com.codecool.moviedb.controller.liked_movies;

import com.codecool.moviedb.dao.DislikedMovieDAO;
import com.codecool.moviedb.dao.LikedMovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/add/liked-movie/{id}")
@CrossOrigin("*")
public class AddLikedMovieController {

    @Autowired
    LikedMovieDAO likedMovieDAO;

    @Autowired
    DislikedMovieDAO dislikedMovieDAO;

    @GetMapping
    public void addLikedMovie(@PathVariable String id) {
        likedMovieDAO.addMovie(id);
        dislikedMovieDAO.deleteMovie(id);

        System.out.println(likedMovieDAO.getAllLikedMovies());
        System.out.println(dislikedMovieDAO.getAllDislikedMovies());
    }
}

