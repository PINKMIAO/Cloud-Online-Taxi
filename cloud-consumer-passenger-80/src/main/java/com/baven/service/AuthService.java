package com.baven.service;

import com.baven.dto.ResponseResult;

public interface AuthService {

    ResponseResult auth(String passengerPhone, String code);

}
