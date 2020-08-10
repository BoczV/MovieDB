package com.codecool.moviedb.controller.watched_movies;

import com.codecool.moviedb.model.User;
import com.codecool.moviedb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/delete-watched/add-watch/{id}")
@CrossOrigin("*")
public class DeleteWatchedAddWatchedController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public void deleteWatchedAddWatch(@PathVariable String id) {
        User dummyIsti = userRepository.getOne(1L);
        dummyIsti.getWatchedMovies().remove(id);
        dummyIsti.getWatchMovies().add(id);
        userRepository.save(dummyIsti);
    }
}


