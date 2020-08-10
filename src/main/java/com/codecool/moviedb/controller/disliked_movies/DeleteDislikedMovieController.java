package com.codecool.moviedb.controller.disliked_movies;

import com.codecool.moviedb.dao.DislikedMovieDAO;
import com.codecool.moviedb.dao.LikedMovieDAO;
import com.codecool.moviedb.model.User;
import com.codecool.moviedb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/add/disliked-movie/{id}")
@CrossOrigin("*")
public class DeleteDislikedMovieController {

    @Autowired
    private UserRepository userRepository;

    public void deleteDislikeMovie(@PathVariable String id) {
        User dummyIsti = userRepository.getOne(1L);
        dummyIsti.getDislikedMovies().remove(id);
        dummyIsti.getLikedMovies().add(id);
        userRepository.save(dummyIsti);
    }
}
