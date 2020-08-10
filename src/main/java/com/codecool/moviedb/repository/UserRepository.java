package com.codecool.moviedb.repository;

import com.codecool.moviedb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
