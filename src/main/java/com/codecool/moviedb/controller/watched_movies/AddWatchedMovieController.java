package com.codecool.moviedb.controller.watched_movies;

import com.codecool.moviedb.dao.WatchListMemDao;
import com.codecool.moviedb.dao.WatchedMovieDAO;
import com.codecool.moviedb.model.User;
import com.codecool.moviedb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/add/watched-movie/{id}")
@CrossOrigin("*")
public class AddWatchedMovieController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WatchedMovieDAO watchedMovieDAO;

    @Autowired
    WatchListMemDao watchListMemDao;

    @GetMapping
    public void addWatchedMovie(@PathVariable String id) {
        User dummyIsti = userRepository.getOne(1L);
        dummyIsti.getWatchedMovies().add(id);
        userRepository.save(dummyIsti);
    }
}
