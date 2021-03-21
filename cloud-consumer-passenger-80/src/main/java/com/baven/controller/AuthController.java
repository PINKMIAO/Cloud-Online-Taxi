package com.baven.controller;

import com.baven.dto.ResponseResult;
import com.baven.dto.cloudcomsumerpassenger80.request.UserAuthRequest;
import com.baven.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody @Validated UserAuthRequest request) {

        String passengerPhone = request.getPassengerPhone();
        String code = request.getCode();

        return authService.auth(passengerPhone, code);
    }


}
