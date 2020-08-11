package com.codecool.moviedb.controller.disliked_movies;

import com.codecool.moviedb.model.User;
import com.codecool.moviedb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/add/disliked-movie/{id}")
@CrossOrigin("*")
public class AddDislikedMovieController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public void addDislikedMovie(@PathVariable String id) {
        User dummyIsti = userRepository.getOne(1L);
        dummyIsti.getDislikedMovies().add(id);
        dummyIsti.getLikedMovies().remove(id);
        userRepository.save(dummyIsti);
    }
}
