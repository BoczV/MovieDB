package com.codecool.moviedb.controller.watched_movies;

import com.codecool.moviedb.components.FindUserByCookie;
import com.codecool.moviedb.components.MovieAPI;
import com.codecool.moviedb.model.User;
import com.codecool.moviedb.repository.UserRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/all-watched-movies")
@CrossOrigin("*")
public class AllWatchedMovieController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieAPI movieAPI;

    @Autowired
    private FindUserByCookie findUserByCookie;

    @GetMapping("/{language}")
    public String getAllWatchedMovies(@PathVariable("language") String language) throws IOException, JSONException {
        User actualUser = findUserByCookie.findUser();
        return getMoviesByIDAPICall((actualUser.getWatchedMovies()), language);
    }

    public String getMoviesByIDAPICall(Set<String> ids, String language) throws IOException, JSONException {
        List<String> result = new ArrayList<>();
        for (String id : ids){
            result.add(movieAPI.getMovieByIdByLanguage(id, language));
        }
        return result.toString();
    }
}
