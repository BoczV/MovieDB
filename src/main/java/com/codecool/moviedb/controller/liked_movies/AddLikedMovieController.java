package com.codecool.moviedb.controller.liked_movies;

import com.codecool.moviedb.model.User;
import com.codecool.moviedb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/add/liked-movie/{id}")
@CrossOrigin("*")
public class AddLikedMovieController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public void addLikedMovie(@PathVariable String id) {
        User dummyIsti = userRepository.getOne(1L);
        dummyIsti.getLikedMovies().add(id);
        dummyIsti.getDislikedMovies().remove(id);
        userRepository.save(dummyIsti);
    }
}
