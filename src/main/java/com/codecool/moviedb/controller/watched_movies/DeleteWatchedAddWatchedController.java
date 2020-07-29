package com.codecool.moviedb.controller.watched_movies;

import com.codecool.moviedb.dao.WatchListMemDao;
import com.codecool.moviedb.dao.WatchedMovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delete-watched/add-watch/{id}")
@CrossOrigin("*")
public class DeleteWatchedAddWatchedController {

    @Autowired
    WatchedMovieDAO watchedMovieDAO;

    @Autowired
    WatchListMemDao watchListMemDao;

    @GetMapping
    public void deleteWatchedAddWatch(@PathVariable String id) {
        watchedMovieDAO.deleteMovie(id);
        watchListMemDao.addMovieToWatchList(id);
        System.out.println(watchedMovieDAO.getAllWatchedMovieIDs());
    }
}


