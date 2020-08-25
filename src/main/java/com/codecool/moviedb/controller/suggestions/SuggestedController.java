package com.codecool.moviedb.controller.suggestions;

import com.codecool.moviedb.components.FindUserByCookie;
import com.codecool.moviedb.components.MovieAPI;
import com.codecool.moviedb.model.User;
import com.codecool.moviedb.repository.UserRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping("/recommendation")
@CrossOrigin("*")
public class SuggestedController {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    MovieAPI movieAPI;

    @Autowired
    private FindUserByCookie findUserByCookie;
    
    @GetMapping("/suggested/{language}")
    public String getSuggested(@PathVariable("language") String language) throws IOException, JSONException {
        User actualUser = findUserByCookie.findUser();
        Set<String> likedMovies = actualUser.getLikedMovies();
        return movieAPI.getAllSuggestedMovies(likedMovies, language);
    }

    @GetMapping("/not-suggested/{language}")
    public String getNotSuggested(@PathVariable("language") String language) throws IOException, JSONException {
        User actualUser = findUserByCookie.findUser();
        Set<String> dislikedMovies = actualUser.getDislikedMovies();
        return movieAPI.getAllSuggestedMovies(dislikedMovies, language);
    }
}
