package com.codecool.moviedb.controller.liked_movies;

import com.codecool.moviedb.dao.LikedMovieDAO;
import com.codecool.moviedb.model.User;
import com.codecool.moviedb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delete/liked-movie/{id}")
@CrossOrigin("*")
public class    DeleteLikedMovieController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public void deleteLikedMovie(@PathVariable String id) {
        User dummyIsti = userRepository.getOne(1L);
        dummyIsti.getLikedMovies().remove(id);
        userRepository.save(dummyIsti);
    }
}
