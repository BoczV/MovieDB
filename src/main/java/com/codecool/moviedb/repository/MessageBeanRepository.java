package com.codecool.moviedb.repository;

import com.codecool.moviedb.chat.MessageBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageBeanRepository  extends JpaRepository<MessageBean, Long> {

    public List<MessageBean> findAllByMovieId(Long movieId);

}
