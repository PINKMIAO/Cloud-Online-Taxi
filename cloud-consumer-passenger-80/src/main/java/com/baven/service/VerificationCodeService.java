package com.baven.service;


import com.baven.dto.ResponseResult;

public interface VerificationCodeService {

    public ResponseResult send(String phoneNumber);

}
