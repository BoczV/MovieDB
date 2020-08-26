package com.codecool.moviedb.controller;

import com.codecool.moviedb.chat.MessageBean;
import com.codecool.moviedb.repository.MessageBeanRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/get-chat")
@CrossOrigin("*")
public class ChatController {
    @Autowired
    MessageBeanRepository messageBeanRepository;

    @GetMapping("/{movieId}")
    public String getChatByMovieId(@PathVariable("movieId") String movieId) throws JSONException {
        System.out.println("élek");
        List<MessageBean> messages = messageBeanRepository.findAllByMovieId(Long.parseLong(movieId));
        System.out.println(messages);
        JSONArray jsonMessages = new JSONArray();
        for (MessageBean messageBean: messages) {
            JSONObject messageJsonObject = new JSONObject();
            messageJsonObject.put("message", messageBean.getMessage());
            messageJsonObject.put("name", messageBean.getName());
            jsonMessages.put(messageJsonObject);
        }
        System.out.println(jsonMessages.toString());
        return jsonMessages.toString();

    }
}
