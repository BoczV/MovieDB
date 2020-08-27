package com.codecool.moviedb.chat;



import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class MessageBean {
    private String name;
    private String message;

    private Long movieId;

    @Id
    @GeneratedValue
    private Long id;

    private String date;
}
