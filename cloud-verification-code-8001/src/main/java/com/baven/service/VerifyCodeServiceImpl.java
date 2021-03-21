package com.baven.service;

import com.baven.constant.CommonStatusEnum;
import com.baven.constant.IdentityConstant;
import com.baven.constant.RedisKeyPrefixConstant;
import com.baven.dto.ResponseResult;
import com.baven.dto.cloudverificationcode8001.response.VerifyCodeResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // 生成验证码，并将验证码存进Redis中
    @Override
    public ResponseResult<VerifyCodeResponse> generate(int identity, String phoneNumber) {

        String code = String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, 5)));

        String keyPre = generateKeyPreByIdentity(identity);
        String key = keyPre + phoneNumber;

        BoundValueOperations<String, String> codeRedis = redisTemplate.boundValueOps(key);
        codeRedis.set(code,2, TimeUnit.MINUTES);

        VerifyCodeResponse data = new VerifyCodeResponse();
        data.setCode(code);
        return ResponseResult.success(data);

    }
    private String generateKeyPreByIdentity(int identity) {
        String pre = "";
        if (identity == IdentityConstant.PASSENGER) {
            pre = RedisKeyPrefixConstant.PASSENGER_LOGIN_CODE_KEY_PRE;
        } else if (identity == IdentityConstant.DRIVER) {
            pre = RedisKeyPrefixConstant.DRIVER_LOGIN_CODE_KEY_PRE;
        }
        return pre;
    }

    // 验证验证码有效性
    @Override
    public ResponseResult verify(int identity, String phoneNumber, String code) {

        String keyPre = generateKeyPreByIdentity(identity);
        String key = keyPre + phoneNumber;

        BoundValueOperations<String, String> codeRedis = redisTemplate.boundValueOps(key);
        String redisCode = codeRedis.get();
        System.out.println(redisCode);
        if (StringUtils.isNotBlank(code)
                && StringUtils.isNotBlank(redisCode)
                && code.trim().equals(redisCode.trim())) {
            return ResponseResult.success("");
        } else {
            return ResponseResult.fail(CommonStatusEnum.VERIFY_CODE_ERROR.getCode(), CommonStatusEnum.VERIFY_CODE_ERROR.getMess());
        }
    }


}
