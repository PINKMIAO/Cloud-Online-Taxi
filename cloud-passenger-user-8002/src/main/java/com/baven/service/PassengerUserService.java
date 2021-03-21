package com.baven.service;

import com.baven.dto.ResponseResult;

public interface PassengerUserService {

    ResponseResult login(String passengerPhone);
    ResponseResult logout(String token);

}
