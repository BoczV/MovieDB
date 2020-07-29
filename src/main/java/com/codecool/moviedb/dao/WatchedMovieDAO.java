package com.codecool.moviedb.dao;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class WatchedMovieDAO {

    private Set<String> watchedMovies = new HashSet<String>();

    public WatchedMovieDAO() {
    }

    public void addMovie(String id){
        watchedMovies.add(id);
    }

    public void deleteMovie(String id){
        watchedMovies.remove(id);
    }

    public Set<String> getAllWatchedMovieIDs(){
        return watchedMovies;
    }
}
