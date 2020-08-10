package com.codecool.moviedb.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false, unique = true)
    private String password;

    @Singular
    @ElementCollection
    private Set<Long> likedMovies;

    @Singular
    @ElementCollection
    private Set<Long> dislikedMovies;

    @Singular
    @ElementCollection
    private Set<Long> watchedMovies;

    @Singular
    @ElementCollection
    private Set<Long> watchMovies;

}
