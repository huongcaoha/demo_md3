package com.ra.base_spring_mvc.model.entity;



import lombok.*;

import javax.persistence.*;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String statusName;
}
