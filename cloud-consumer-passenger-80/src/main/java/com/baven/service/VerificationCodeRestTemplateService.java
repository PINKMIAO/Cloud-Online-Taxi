package com.baven.service;


import com.baven.dto.ResponseResult;

public interface VerificationCodeRestTemplateService {

    ResponseResult generatorCode(int identity, String phoneNumber);

    ResponseResult verifyCode(int identity, String phoneNumber, String code);

}
