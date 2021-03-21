package com.baven.service;

import com.baven.constant.RedisKeyPrefixConstant;
import com.baven.dto.ResponseResult;
import com.baven.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
public class PassengerUserServiceImpl implements PassengerUserService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public ResponseResult login(String passengerPhone) {
        // 源码中还将数据以及一些默认的用户信息存进数据库
        int passengerId = 0;
        HashMap<String, String> map = new HashMap<>();
        map.put("passengerId",passengerId+"");
        String token = JwtUtil.getToken(map);
        // 存入redis，设置过期时间。
        BoundValueOperations<String, String> stringStringBoundValueOperations
                = redisTemplate.boundValueOps(RedisKeyPrefixConstant.PASSENGER_LOGIN_TOKEN_APP_KEY_PRE + passengerId);
        stringStringBoundValueOperations.set(token,30, TimeUnit.DAYS);

        return ResponseResult.success(token);
    }

    @Override
    public ResponseResult logout(String token) {
        return null;
    }
}
