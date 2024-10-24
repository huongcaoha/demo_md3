package com.ra.base_spring_mvc.controller.user;

import com.ra.base_spring_mvc.model.entity.zaloPay.ZaloPayRequest;
import com.ra.base_spring_mvc.model.entity.zaloPay.ZaloPayResponse;
import com.ra.base_spring_mvc.model.entity.zaloPay.ZaloPayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PaymentController {

    @Value("${zalopay.partnerCode}")
    private String partnerCode;

    @Value("${zalopay.secretKey}")
    private String secretKey;

    @PostMapping("/create-zalopay-qr")
    public ZaloPayResponse createZaloPayQR(@RequestBody ZaloPayRequest request) {
        // Tạo MAC cho yêu cầu
        String macData = partnerCode + "|" + request.getAppId() + "|" + request.getAppTransId() + "|" +
                request.getAmount() + "|" + request.getDescription() + "|" + request.getBankCode() + "|" +
                request.getEmbedData() + "|" + request.getItem() + "|" + secretKey;

        String mac = ZaloPayUtils.generateSignature(macData, secretKey);
        request.setMac(mac);

        // Gửi yêu cầu đến ZaloPay
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://sandbox.zalopay.com.vn/v001/tpe/create";
        ZaloPayResponse response = restTemplate.postForObject(url, request, ZaloPayResponse.class);

        return response; // Trả về phản hồi từ ZaloPay
    }
}