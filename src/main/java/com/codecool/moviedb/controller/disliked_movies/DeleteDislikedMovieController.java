package com.codecool.moviedb.controller.disliked_movies;

import com.codecool.moviedb.dao.DislikedMovieDAO;
import com.codecool.moviedb.dao.LikedMovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/add/disliked-movie/{id}")
@CrossOrigin("*")
public class DeleteDislikedMovieController {

    @Autowired
    LikedMovieDAO likedMovieDAO;

    @Autowired
    DislikedMovieDAO dislikedMovieDAO;

    public void deleteDislikeMovie(@PathVariable String id) {
        dislikedMovieDAO.deleteMovie(id);

    }
}
