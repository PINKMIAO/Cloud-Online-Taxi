package com.baven.dto;

import com.baven.constant.CommonStatusEnum;
import com.baven.constant.TestColor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class ResponseResult<T> implements Serializable {
    private static final long serialVesionUID = 1L;

    private int code;
    private String message;
    private T data;

    /**
     * 返回成功数据（status：1）
     *
     * @param data 数据内容
     * @param <T>  数据类型
     * @return ResponseResult实例
     */
    public static <T> ResponseResult success(T data) {              // <T> 表示传入的参数是泛型，与static起的表示静态一样
        return new ResponseResult()
                .setCode(CommonStatusEnum.SUCCESS.getCode())
                .setMessage(CommonStatusEnum.SUCCESS.getMess())
                .setData(data);
    }
    /**
     * 返回错误数据（status：500）
     *
     * @param data 错误内容
     * @param <T>  数据类型
     * @return ResponseResult实例
     */
    public static <T> ResponseResult fail(T data) {
        return new ResponseResult()
                .setCode(CommonStatusEnum.INTERNAL_SERVER_EXCEPTION.getCode())
                .setMessage(CommonStatusEnum.INTERNAL_SERVER_EXCEPTION.getMess())
                .setData(data);
    }
    public static ResponseResult fail(int code, String message) {
        return new ResponseResult().setCode(code).setMessage(message);
    }

}
