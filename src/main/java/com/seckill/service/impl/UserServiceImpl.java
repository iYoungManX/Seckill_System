package com.seckill.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seckill.exception.GlobalException;
import com.seckill.mapper.UserMapper;
import com.seckill.pojo.User;
import com.seckill.service.IUserService;
import com.seckill.utils.CookieUtil;
import com.seckill.utils.MD5Util;
import com.seckill.utils.UUIDUtil;
import com.seckill.vo.LoginVO;
import com.seckill.vo.RespBean;
import com.seckill.vo.RespBeanEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author iYoungMan
 * @since 2022-06-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public RespBean doLogin(LoginVO loginVO, HttpServletRequest request, HttpServletResponse response){
        String mobile= loginVO.getMobile();
        String password = loginVO.getPassword();


        User user = userMapper.selectById(mobile);

        if(user ==null){
           throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }

        if(!MD5Util.fromPassToDBPass(password,user.getSalt()).equals(user.getPassword())){
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }

        // generate cookie
        String ticket = UUIDUtil.uuid();
        redisTemplate.opsForValue().set("user:"+ticket, user);
        request.getSession().setAttribute(ticket,user);
        CookieUtil.setCookie(request,response,"userTicket",ticket);
        return RespBean.success(ticket);

    }

    @Override
    public User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response) {
        if(StringUtils.isEmpty(userTicket)){
            return null;
        }
        User user = (User) redisTemplate.opsForValue().get("user:"+userTicket);

        if(user!=null){
            CookieUtil.setCookie(request,response,"userTicket",userTicket);
        }

        return user;
    }



    @Override
    public RespBean updatePassword(String userTicket, String password, HttpServletRequest request,
                                   HttpServletResponse response) {
        User user = getUserByCookie(userTicket, request, response);
        if (user == null) {
            throw new GlobalException(RespBeanEnum.MOBILE_NOT_EXIST);
        }
        user.setPassword(MD5Util.inputPassToDBPass(password, user.getSalt()));
        int result = userMapper.updateById(user);
        if (1 == result) {
            //删除Redis
            redisTemplate.delete("user:" + userTicket);
            return RespBean.success();
        }
        return RespBean.error(RespBeanEnum.PASSWORD_UPDATE_FAIL);
    }
}
