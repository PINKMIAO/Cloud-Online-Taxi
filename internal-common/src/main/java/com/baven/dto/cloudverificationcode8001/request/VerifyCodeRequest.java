package com.baven.dto.cloudverificationcode8001.request;

import lombok.Data;

@Data
public class VerifyCodeRequest {

    private int identity;
    private String phoneNumber;
    private String code;

}
