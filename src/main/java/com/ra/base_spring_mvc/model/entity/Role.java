package com.ra.base_spring_mvc.model.entity;


import lombok.*;
import com.ra.base_spring_mvc.model.constants.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

}
