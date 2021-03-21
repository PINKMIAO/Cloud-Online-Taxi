package com.baven.constant;

import lombok.Getter;

public enum TestColor {
    black(1,"这是黑色"),
    red(2,"这是红色"),
    green(3,"这是绿色"),
    yellow(4,"这是黄色");

//  ===============================================
    @Getter
    private final int code;
    @Getter
    private final String message;
    private TestColor(int code, String message){        // 要想常量有值，这里先写，这样上面再创建常量的时候就必须把参数写上

        this.code = code;
        this.message = message;
    }
//  ===============================================
}
