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
    LOGIN_ERROR(500210,"Incorrect username or password");
    private final Integer code;
    private final String message;
}
