package com.ra.base_spring_mvc.model.entity;


import lombok.*;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user ;

    private String content ;

    private long id_comment_parent ;

    private boolean status = false;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date created_at = new Date();
}
