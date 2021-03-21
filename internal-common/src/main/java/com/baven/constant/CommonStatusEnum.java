package com.baven.constant;

import lombok.Getter;

public enum CommonStatusEnum {
    /**短信验证码服务	100  01-100  99*/
    VERIFY_CODE_ERROR(10001,"短信验证码验证失败"),

    /**一分钟内相同验证码错误达3次，请1分钟后登录*/
    VERIFICATION_ONE_MIN_ERROR(10002,"您同一个错误验证码点击登录的次数过多,请于 1分钟后再重试"),
    /**一小时内验证码错误达3次，请10分钟后登录*/
    VERIFICATION_TEN_MIN_ERROR(10003,"您登录失败的次数过多,请于 10分钟后再重试"),
    /**一小时内验证码错误达5次，请24小时后登录*/
    VERIFICATION_ONE_HOUR_ERROR(10004,"您今天登录失败的次数过多,请于 24小时后再重试"),


    /**api-passenger 乘客api  101 01-101 99*/
    PHONENUMBER_EMPTY(10101,"手机号为空"),
    PHONENUMBER_ERROR(10102,"手机号格式不正确"),

    /**n内部服务操作异常*/
    INTERNAL_SERVER_EXCEPTION(-1, "exception"),
    SUCCESS(0,"success"),
    FAIL(1, "fail");


    //  ===============================================
    @Getter
    private final int code;
    @Getter
    private final String mess;
    CommonStatusEnum(int code, String mess) {
        this.code = code;
        this.mess = mess;
    }
    //  ===============================================

}
