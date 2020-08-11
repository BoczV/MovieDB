package com.codecool.moviedb.controller.liked_movies;

import com.codecool.moviedb.components.MovieAPI;
import com.codecool.moviedb.repository.UserRepository;
import org.json.JSONException;
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

    @GetMapping("/{language}")
    public String getAllLikedMovie(@PathVariable("language") String language) throws IOException, JSONException {
        return getMoviesByIDAPICall(userRepository.getOne(1L).getLikedMovies(), language);
    }

    public String getMoviesByIDAPICall(Set<String> ids, String language) throws IOException, JSONException {
        List<String> result = new ArrayList<>();
        for (String id : ids){
            result.add(movieAPI.getMovieByIdByLanguage(id, language));
        }
        return result.toString();
    }
}

