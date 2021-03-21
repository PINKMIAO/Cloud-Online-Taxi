package com.baven.controller;

import com.baven.constant.IdentityConstant;
import com.baven.dto.ResponseResult;
import com.baven.dto.cloudcomsumerpassenger80.request.ShortMsgRequest;
import com.baven.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/verify-code")
public class VerificationCodeController {

    @Autowired
    VerificationCodeService verificationCodeService;

    @GetMapping("/send")
    public ResponseResult send(@RequestBody @Validated ShortMsgRequest request) {
        return verificationCodeService.send(request.getPhoneNumber());
    }

}
