package com.ra.base_spring_mvc.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    @NotBlank
    private String receive_name ;
    @NotBlank
    private String receive_address;
    @NotBlank
    private String receive_phone;
    private String note ;
}
