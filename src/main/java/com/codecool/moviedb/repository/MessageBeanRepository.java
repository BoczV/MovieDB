package com.codecool.moviedb.repository;

import com.codecool.moviedb.chat.MessageBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MessageBeanRepository  extends JpaRepository<MessageBean, Long> {

    public List<MessageBean> findAllByMovieId(Long movieId);

    @Transactional
    @Modifying
    @Query("DELETE FROM MessageBean m WHERE m.id = :id")
    public void deleteMessageBeanById(@Param("id") Long id);

}
