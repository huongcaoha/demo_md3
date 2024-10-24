package com.ra.base_spring_mvc.model.entity.zaloPay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class ZaloPayRequest {
    private String appId;
    private String appUser;
    private String appTransId;
    private String amount;
    private String description;
    private String bankCode;
    private String embedData;
    private String item;
    private String mac;
}
