package com.codecool.moviedb.controller.liked_movies;

import com.codecool.moviedb.components.FindUserByCookie;
import com.codecool.moviedb.model.User;
import com.codecool.moviedb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delete/liked-movie/{id}")
@CrossOrigin("*")
public class DeleteLikedMovieController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private FindUserByCookie findUserByCookie;

    @GetMapping
    public void deleteLikedMovie(@PathVariable String id) {
        User actualUser = findUserByCookie.findUser();
        actualUser.getLikedMovies().remove(id);
        userRepository.save(actualUser);
    }
}
