package com.ra.base_spring_mvc.model.entity.dto;

import com.ra.base_spring_mvc.validator.category.CategoryNameUnique;
import lombok.*;

import org.springframework.web.multipart.MultipartFile;


import org.springframework.web.multipart.MultipartFile;


import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private int id;

    @Column(name = "categoryName" ,unique = true)
    @NotBlank(message = "category name can not blank !")
    @CategoryNameUnique
    private String categoryName ;

    private MultipartFile fileImageCategory ;

    private String description ;
    private boolean status ;
}
