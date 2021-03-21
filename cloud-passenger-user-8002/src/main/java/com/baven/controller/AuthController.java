package com.baven.controller;

import com.baven.dto.ResponseResult;
import com.baven.dto.cloudpassengeruser8002.request.LoginRequest;
import com.baven.service.PassengerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    PassengerUserService passengerUserService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginRequest request) {
        String passengerPhone = request.getPassengerPhone();
        return passengerUserService.login(passengerPhone);
    }

}
