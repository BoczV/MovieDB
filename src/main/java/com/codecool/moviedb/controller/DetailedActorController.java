package com.codecool.moviedb.controller;

import com.codecool.moviedb.components.MovieAPI;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/actor")
@CrossOrigin("*")
public class DetailedActorController {
    @Autowired
    MovieAPI movieAPI;

    @GetMapping("/{actorId}/{language}")
    public String getActorById(@PathVariable("actorId") String actorId, @PathVariable("language") String language) throws IOException, JSONException {
        String result = movieAPI.getActorByIdByLanguage(actorId, language);
        JSONObject jsonObject = new JSONObject(result);
        return jsonObject.toString();
    }
}
