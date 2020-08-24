package com.codecool.moviedb.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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
    private Set<String> likedMovies;

    @Singular
    @ElementCollection
    private Set<String> dislikedMovies;

    @Singular
    @ElementCollection
    private Set<String> watchedMovies;

    @Singular
    @ElementCollection
    private Set<String> watchMovies;

    // roles of the user (ADMIN, USER,..)
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();
}
