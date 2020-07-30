package com.codecool.moviedb.controller.liked_movies;

import com.codecool.moviedb.dao.LikedMovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delete/liked-movie/{id}")
@CrossOrigin("*")
public class    DeleteLikedMovieController {

    @Autowired
    LikedMovieDAO likedMovieDAO;

    @GetMapping
    public void deleteWatchedMovie(@PathVariable String id) {
        likedMovieDAO.deleteMovie(id);
        System.out.println(likedMovieDAO.getAllLikedMovies());
    }
}
