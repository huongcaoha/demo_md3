package com.ra.base_spring_mvc.model.entity;


import lombok.*;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user ;

    @ManyToOne
    @JoinColumn(name = "product_id" , referencedColumnName = "id")
    private Product product ;

    private int rate ;

    private String content ;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date created_at = new Date();
}
