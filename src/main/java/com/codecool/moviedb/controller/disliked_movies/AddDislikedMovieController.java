package com.codecool.moviedb.controller.disliked_movies;

import com.codecool.moviedb.components.FindUserByCookie;
import com.codecool.moviedb.model.User;
import com.codecool.moviedb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/add/disliked-movie/{id}")
@CrossOrigin("*")
public class AddDislikedMovieController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FindUserByCookie findUserByCookie;

    @GetMapping
    public void addDislikedMovie(@PathVariable String id) {
        User actualUser = findUserByCookie.findUser();
        actualUser.getDislikedMovies().add(id);
        actualUser.getLikedMovies().remove(id);
        userRepository.save(actualUser);
    }
}
