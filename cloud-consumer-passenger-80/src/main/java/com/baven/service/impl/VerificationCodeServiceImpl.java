package com.baven.service.impl;


import com.baven.constant.CommonStatusEnum;
import com.baven.constant.IdentityConstant;
import com.baven.dto.ResponseResult;
import com.baven.dto.cloudverificationcode8001.response.VerifyCodeResponse;
import com.baven.service.VerificationCodeRestTemplateService;
import com.baven.service.VerificationCodeService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 多次校验
 */
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Autowired
    VerificationCodeRestTemplateService verificationCodeRestTemplateService;

    @Override
    public ResponseResult send(String phoneNumber) {
        ResponseResult result = verificationCodeRestTemplateService.generatorCode(IdentityConstant.PASSENGER, phoneNumber);
        VerifyCodeResponse verifyCodeResponse = null;
        if (result.getCode() == CommonStatusEnum.SUCCESS.getCode()) {
//            VerifyCodeResponse data1 = (VerifyCodeResponse) result.getData();
            // 这两行的目的是将 在 result中的 data对象 赋给 verifyCodeResponse
            JSONObject data = JSONObject.fromObject(result.getData().toString());
            verifyCodeResponse = (VerifyCodeResponse) JSONObject.toBean(data, VerifyCodeResponse.class);
        } else {
            return ResponseResult.fail("获取验证码失败");
        }
        String code = verifyCodeResponse.getCode();


        return result;
    }
}
