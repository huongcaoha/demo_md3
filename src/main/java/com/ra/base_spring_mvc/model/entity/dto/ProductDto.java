package com.ra.base_spring_mvc.model.entity.dto;

import com.ra.base_spring_mvc.model.entity.Category;
import com.ra.base_spring_mvc.validator.category.CategoryNameUnique;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private int id;

    @Column(name = "product_name",unique = true)
    @NotBlank(message = "Product name can not blank !")
    private String productName;

    private MultipartFile fileImageProduct ;

    private Category category;

    private Date created_at = new Date();

    private Date updated_at ;

    private String description;
}
