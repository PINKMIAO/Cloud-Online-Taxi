package com.baven.controller;

import com.baven.dto.ResponseResult;
import com.baven.dto.cloudverificationcode8001.request.VerifyCodeRequest;
import com.baven.service.VerifyCodeService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verify-code")
@Slf4j
public class VerifyCodeController {

    @Autowired
    VerifyCodeService verifyCodeService;

    @GetMapping("/generate/{identity}/{phoneNumber}")
    public ResponseResult generate(@PathVariable("identity") int identity, @PathVariable("phoneNumber") String phoneNumber) {
        log.info("/generate/{identity}/{phoneNumber} : 身份类型：" + identity + "，手机号：" + phoneNumber);
        return verifyCodeService.generate(identity, phoneNumber);
    }


    @PostMapping("/verify")
    public ResponseResult verifyCode(@RequestBody VerifyCodeRequest request) {
        log.info("/verify-code/verify : request: " + JSONObject.fromObject(request));
        int identity = request.getIdentity();
        String phoneNumber = request.getPhoneNumber();
        String code = request.getCode();
        return verifyCodeService.verify(identity, phoneNumber, code);
    }


}
