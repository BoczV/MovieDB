package com.codecool.moviedb.chat;

import com.codecool.moviedb.repository.MessageBeanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;

@Controller
public class SocketController {
    @Autowired
    MessageBeanRepository messageBeanRepository;

    @MessageMapping("/user-all")
    @SendTo("/topic/user")
    public MessageBean sendToAll(@Payload MessageBean message) {
        message.setDate(new Date());
        messageBeanRepository.save(message);
        deleteOldMessages(message.getMovieId());
        return message;
    }

    private void deleteOldMessages(Long movieId) {
        List<MessageBean> messages = messageBeanRepository.findAllByMovieId(movieId);
        if (messages.size() > 20) {
            messageBeanRepository.deleteMessageBeanById(messages.get(0).getId());
        }
    }

}
