package com.baven.service;

import com.baven.dto.ResponseResult;
import com.baven.dto.cloudverificationcode8001.response.VerifyCodeResponse;

public interface VerifyCodeService {

    /** 根据身份和手机号生成验证码 */
    ResponseResult<VerifyCodeResponse> generate(int identity, String phoneNumber);

    /** 校验身份，手机号，验证码的合法性 */
    ResponseResult verify(int identity, String phoneNumber, String code);

}
