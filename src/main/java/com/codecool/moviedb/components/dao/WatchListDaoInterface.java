package com.codecool.moviedb.components.dao;

import java.util.List;

public interface WatchListDaoInterface {
    public List<String> getWatchList();
    public void addMovieToWatchList(String movieId);
    public void removeMovieFromWatchList(String movieId);

}
