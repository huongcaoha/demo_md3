package com.ra.base_spring_mvc.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @Column(name = "categoryName" ,unique = true)
    @NotBlank(message = "category name can not blank !")
    private String categoryName ;
    private String image ;
    private String description ;
    private boolean status ;
}
