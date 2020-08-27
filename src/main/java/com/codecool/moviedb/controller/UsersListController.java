package com.codecool.moviedb.controller;


import com.codecool.moviedb.model.User;
import com.codecool.moviedb.repository.UserRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UsersListController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String returnUsers() throws JSONException {
        List<User> users = userRepository.findAll();
        JSONArray jsonArray = new JSONArray();
        for (User user : users) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", user.getId());
            jsonObject.put("userName", user.getUserName());
            jsonObject.put("email", user.getEmail());
            jsonObject.put("roles", user.getRoles());
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

    @Modifying
    @Transactional
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userRepository.deleteById(id);
    }
}
