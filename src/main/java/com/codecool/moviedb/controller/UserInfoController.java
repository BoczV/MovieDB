package com.codecool.moviedb.controller;

import com.codecool.moviedb.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class UserInfoController {

    @GetMapping("/me")
    public String currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getUserName() + "\n" + user.getRoles();
    }
}
