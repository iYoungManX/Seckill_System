package com.seckill.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author iYoungMan
 * @since 2022-06-20
 */
@Data
@TableName("t_user")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("userTele")
    private Long id;

    private String nickname;

    @ApiModelProperty("MD5(MD5(passord+salt)+salt)")
    private String password;

    private String salt;

    @ApiModelProperty("avatar")
    private String head;

    @ApiModelProperty("register time")
    private LocalDateTime registerDate;

    @ApiModelProperty("last_login_date")
    private LocalDateTime lastLoginDate;

    @ApiModelProperty("login time")
    private Integer loginCount;


}
