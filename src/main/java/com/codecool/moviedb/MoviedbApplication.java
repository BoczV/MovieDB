package com.codecool.moviedb;

import com.codecool.moviedb.model.User;
import com.codecool.moviedb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class MoviedbApplication {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(MoviedbApplication.class, args);
    }

    /*@Bean
    @Profile("production")
    public CommandLineRunner init(){
        return args -> {
            User user = User.builder().userName("Isti").password("ugyi").likedMovie(4L).dislikedMovie(3L).watchedMovie(5L).watchMovie(6L).build();
            userRepository.save(user);

            User user1 = User.builder().userName("Zsuzsi").password("dog").likedMovie(19L).dislikedMovie(18L).watchedMovie(15L).watchMovie(16L).build();
            userRepository.save(user1);
        };

    }*/

}
