package com.baven.service.impl;

import com.baven.constant.CommonStatusEnum;
import com.baven.constant.IdentityConstant;
import com.baven.dto.ResponseResult;
import com.baven.service.AuthService;
import com.baven.service.PassengerUserService;
import com.baven.service.VerificationCodeRestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    VerificationCodeRestTemplateService verificationCodeRestTemplateService;
    @Autowired
    PassengerUserService passengerUserService;

    @Override
    public ResponseResult auth(String passengerPhone, String code) {
        // 验证6位验证码
        ResponseResult result = verificationCodeRestTemplateService.verifyCode(IdentityConstant.PASSENGER, passengerPhone, code);
        if (result.getCode() != CommonStatusEnum.SUCCESS.getCode()) {
            return ResponseResult.fail("登入失败，验证码校验失败");
        }

        result = passengerUserService.login(passengerPhone);
        if (result.getCode() != CommonStatusEnum.SUCCESS.getCode()) {
            return ResponseResult.fail("登入失败，登入失败");
        }
        return result;
    }
}
