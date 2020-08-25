package com.codecool.moviedb.controller.liked_movies;

import com.codecool.moviedb.components.FindUserByCookie;
import com.codecool.moviedb.components.MovieAPI;
import com.codecool.moviedb.model.User;
import com.codecool.moviedb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/all-liked-movies")
@CrossOrigin("*")
public class AllLikedMovieController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieAPI movieAPI;

    @Autowired
    private FindUserByCookie findUserByCookie;

    @GetMapping("/{language}")
    public String getAllLikedMovie(@PathVariable("language") String language) throws IOException {
        User actualUser = findUserByCookie.findUser();
        return getMoviesByIDAPICall(actualUser.getLikedMovies(), language);
    }

    public String getMoviesByIDAPICall(Set<String> ids, String language) throws IOException {
        List<String> result = new ArrayList<>();
        for (String id : ids){
            result.add(movieAPI.getMovieByIdByLanguage(id, language));
        }
        return result.toString();
    }
}
