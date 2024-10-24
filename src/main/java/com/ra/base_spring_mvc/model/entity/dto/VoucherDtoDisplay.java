package com.ra.base_spring_mvc.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class VoucherDtoDisplay {
   private int id ;
    private String code ;
    private double persent ;
    private double min_amount;
    private int quantity;
    private String Description ;
    private Date start_date ;
    private Date end_date ;
    private boolean have ;
}
