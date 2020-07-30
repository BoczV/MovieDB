package com.codecool.moviedb.controller.disliked_movies;

import com.codecool.moviedb.dao.DislikedMovieDAO;
import com.codecool.moviedb.dao.LikedMovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/add/disliked-movie/{id}")
@CrossOrigin("*")
public class AddDislikedMovieController {

    @Autowired
    LikedMovieDAO likedMovieDAO;

    @Autowired
    DislikedMovieDAO dislikedMovieDAO;

    @GetMapping
    public void addDislikedMovie(@PathVariable String id) {
        dislikedMovieDAO.addMovie(id);
        likedMovieDAO.deleteMovie(id);

        System.out.println(likedMovieDAO.getAllLikedMovies());
        System.out.println(dislikedMovieDAO.getAllDislikedMovies());
    }
}
