package com.codecool.moviedb.controller;

import com.codecool.moviedb.chat.MessageBean;
import com.codecool.moviedb.repository.MessageBeanRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
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
        List<MessageBean> messages = messageBeanRepository.findAllByMovieId(Long.parseLong(movieId));
        JSONArray jsonMessages = new JSONArray();
        for (MessageBean messageBean: messages) {
            JSONObject messageJsonObject = new JSONObject();
            messageJsonObject.put("message", messageBean.getMessage());
            messageJsonObject.put("name", messageBean.getName());

            messageJsonObject.put("date", messageBean.getDate());
            jsonMessages.put(messageJsonObject);
        }
        return jsonMessages.toString();

    }
}
