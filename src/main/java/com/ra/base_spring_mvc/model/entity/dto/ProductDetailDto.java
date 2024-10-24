package com.ra.base_spring_mvc.model.entity.dto;

import com.ra.base_spring_mvc.model.entity.Color;
import com.ra.base_spring_mvc.model.entity.Product;
import com.ra.base_spring_mvc.model.entity.Size;
import com.ra.base_spring_mvc.model.entity.Status;
import com.ra.base_spring_mvc.validator.category.CategoryNameUnique;


import lombok.*;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.util.UUID;



@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDto {
    private int id;
    private String sku ;
    private double unit_price;

    private int stock_quantity;

    private MultipartFile fileImage;

    private Status status;

    private int productId;

    private Color color;

    private Size size;
}
