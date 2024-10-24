package com.ra.base_spring_mvc.model.entity;


import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;


@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "colors")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(name = "color_name",unique = true)
    @NotBlank(message = "Product name can not blank !")
    private String colorName;
    private boolean status ;
}
