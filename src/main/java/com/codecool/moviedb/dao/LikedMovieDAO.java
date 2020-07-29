package com.codecool.moviedb.dao;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class LikedMovieDAO {

    private Set<String> LikedMovies = new HashSet<String>();

    public void addMovie(String id) {
        LikedMovies.add(id);
    }

    public void deleteMovie(String id) {
        LikedMovies.remove(id);
    }

    public Set<String> getAllWatchedMovieIDs() {
        return LikedMovies;
    }
}
