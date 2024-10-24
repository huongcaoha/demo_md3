package com.ra.base_spring_mvc.model.entity;

import lombok.*;

import java.util.Date;

import java.util.List;
import java.util.Set;



import javax.persistence.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id ;


    @OneToMany(mappedBy = "product")
    private List<ProductDetail> productDetails;


    @Column(name = "product_name",unique = true)
    @NotBlank(message = "Product name can not blank !")
    private String productName;


    private String image;

    @ManyToOne()
    @JoinColumn(name = "category_id")

    private Category category;

    private Date created_at = new Date();

    private Date updated_at ;

    private String description;


}
