package com.codecool.moviedb.controller.disliked_movies;

import com.codecool.moviedb.components.FindUserByCookie;
import com.codecool.moviedb.model.User;
import com.codecool.moviedb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/add/disliked-movie/{id}")
@CrossOrigin("*")
public class DeleteDislikedMovieController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FindUserByCookie findUserByCookie;

    public void deleteDislikeMovie(@PathVariable String id) {
        User actualUser = findUserByCookie.findUser();
        actualUser.getDislikedMovies().remove(id);
        actualUser.getLikedMovies().add(id);
        userRepository.save(actualUser);
    }
}
