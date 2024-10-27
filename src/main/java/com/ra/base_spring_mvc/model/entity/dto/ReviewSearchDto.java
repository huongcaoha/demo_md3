package com.ra.base_spring_mvc.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewSearchDto {
    private String productName ;
    private int rate ;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date created_at;
}
