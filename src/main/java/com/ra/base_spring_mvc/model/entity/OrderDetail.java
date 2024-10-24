package com.ra.base_spring_mvc.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @ManyToOne
    @JoinColumn(name = "order_id" , referencedColumnName = "id")
    private Order order ;

    @ManyToOne
    @JoinColumn(name = "product_detail_id" , referencedColumnName = "id")
    private ProductDetail productDetail ;

    private int quantity ;
}
