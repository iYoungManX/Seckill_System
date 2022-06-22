package com.seckill.vo;

import com.seckill.validator.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class LoginVO {
    @NotNull
    @IsMobile
    private String mobile;
    @NotNull
    @Length(min=32)
    private String password;
}
