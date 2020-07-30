package com.codecool.moviedb.dao;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DislikedMovieDAO {

    private Set<String> DislikedMovies = new HashSet<String>();

    public void addMovie(String id) {
        DislikedMovies.add(id);
    }

    public void deleteMovie(String id) {
        DislikedMovies.remove(id);
    }

    public Set<String> getAllDislikedMovies() {
        return DislikedMovies;
    }
}

