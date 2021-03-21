package com.baven.service.impl;

import com.baven.dto.ResponseResult;
import com.baven.dto.cloudverificationcode8001.request.VerifyCodeRequest;
import com.baven.service.VerificationCodeRestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/***
 * 用 RestTemplate 远程获取对象
 */
@Service
public class VerificationCodeRestTemplateServiceImpl implements VerificationCodeRestTemplateService {

    @Autowired
    RestTemplate restTemplate;
    public static final String REST_TEMPLATE = "http://CLOUD-VERIFICATION-CODE/verify-code";

    @Override
    public ResponseResult generatorCode(int identity, String phoneNumber) {
        return restTemplate.getForObject(REST_TEMPLATE + "/generate/" + identity + "/" + phoneNumber, ResponseResult.class);
    }

    @Override
    public ResponseResult verifyCode(int identity, String phoneNumber, String code) {
        VerifyCodeRequest request = new VerifyCodeRequest();
        request.setIdentity(identity);
        request.setPhoneNumber(phoneNumber);
        request.setCode(code);
        return restTemplate.postForObject(REST_TEMPLATE + "/verify", request, ResponseResult.class);// 源码里url 还有个斜杆，出错排查这里
    }
}
