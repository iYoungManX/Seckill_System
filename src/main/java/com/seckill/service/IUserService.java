package com.seckill.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.seckill.pojo.User;
import com.seckill.vo.LoginVO;
import com.seckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author iYoungMan
 * @since 2022-06-20
 */
public interface IUserService extends IService<User> {

    RespBean doLogin(LoginVO loginVO, HttpServletRequest request, HttpServletResponse response);
    User getUserByCookie(String userTicket,HttpServletRequest request, HttpServletResponse response);
    RespBean updatePassword(String userTicket, String password, HttpServletRequest request,
                            HttpServletResponse response);

}
