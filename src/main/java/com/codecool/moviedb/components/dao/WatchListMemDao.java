package com.codecool.moviedb.components.dao;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WatchListMemDao implements WatchListDaoInterface {
    private List<String> watchList = new ArrayList<>();

    @Override
    public List<String> getWatchList() {
        return watchList;
    }

    @Override
    public void addMovieToWatchList(String movieId) {
        if (!watchList.contains(movieId)) {
            watchList.add(movieId);
        }
    }

    @Override
    public void removeMovieFromWatchList(String movieId) {
        watchList = watchList.stream().filter(movie -> movie.equals(movieId)).collect(Collectors.toList());
    }
}
