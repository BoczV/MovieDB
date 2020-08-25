package com.codecool.moviedb.controller.watched_movies;

import com.codecool.moviedb.components.FindUserByCookie;
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

    @Autowired
    private FindUserByCookie findUserByCookie;

    @GetMapping
    public void deleteWatchedAddWatch(@PathVariable String id) {
        User actualUser = findUserByCookie.findUser();
        actualUser.getWatchedMovies().remove(id);
        actualUser.getWatchMovies().add(id);
        userRepository.save(actualUser);
    }
}
