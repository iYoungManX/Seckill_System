package com.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {

    SUCCESS(200,"SUCCESS"),
    ERROR(500,"Service Error"),
    MOBILE_ERROR(500210,"Incorrect username or password"),
    LOGIN_ERROR(500210,"Incorrect username or password"),
    BIND_ERROR(500212,"Incorrect parameters"),
    EMPTY_STOCK(500500,"Shortage of stock"),
    REPEAT_ERROR(500501,"Everyone can only get one item");
    private final Integer code;
    private final String message;
}
