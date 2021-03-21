package com.baven.service.impl;

import com.baven.dto.ResponseResult;
import com.baven.dto.cloudpassengeruser8002.request.LoginRequest;
import com.baven.service.PassengerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PassengerUserServiceImpl implements PassengerUserService {

    @Autowired
    RestTemplate restTemplate;

    private static final String REST_TEMPLATE = "http://CLOUD-PASSENGER-USER/auth";

    @Override
    public ResponseResult login(String passengerPhone) {
        LoginRequest login = new LoginRequest();
        login.setPassengerPhone(passengerPhone);
        return restTemplate.postForObject(REST_TEMPLATE + "/login", login, ResponseResult.class);
    }
}
