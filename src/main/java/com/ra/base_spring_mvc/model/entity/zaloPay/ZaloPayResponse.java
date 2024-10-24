package com.ra.base_spring_mvc.model.entity.zaloPay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class ZaloPayResponse {
    private String returnCode;
    private String returnMessage;
    private String qrCodeUrl; // URL mã QR

}
