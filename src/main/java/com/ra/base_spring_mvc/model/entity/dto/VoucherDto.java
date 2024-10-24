package com.ra.base_spring_mvc.model.entity.dto;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.Max;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class VoucherDto {

    @NotBlank(message = "code can not blank !")
    private String code ;

    @NotNull(message = "persent can not null !")

    @Min(1)
    @Max(100)

    private double persent ;

    @Min(0)
    private double min_amount;

    @Min(0)
    @NotNull(message = "quantity can not null !")
    private int quantity;

    private String Description ;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start_date ;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end_date ;
}
