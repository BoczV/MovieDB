package com.codecool.moviedb.controller.disliked_movies;

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
@RequestMapping("/all-disliked-movies")
@CrossOrigin("*")
public class AllDislikedMovieController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    MovieAPI movieAPI;

    @GetMapping
    public String getAllDislikedMovie() throws IOException, JSONException {
        User dummyIsti = userRepository.getOne(1L);
        return getMoviesByIDAPICall(dummyIsti.getDislikedMovies());
    }

    public String getMoviesByIDAPICall(Set<String> ids) throws IOException, JSONException {
        List<String> result = new ArrayList<>();
        for (String id : ids){
            result.add(movieAPI.getMovieById(id));
        }
        return result.toString();
    }
}
