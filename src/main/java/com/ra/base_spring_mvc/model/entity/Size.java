package com.ra.base_spring_mvc.model.entity;


import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sizes")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "size_name", unique = true)
    @NotBlank(message = "Product name can not blank !")
    private String sizeName;
    private boolean status;
}
