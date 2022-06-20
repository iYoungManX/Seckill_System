package com.seckill.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seckill.mapper.UserMapper;
import com.seckill.pojo.User;
import com.seckill.service.IUserService;
import com.seckill.utils.MD5Util;
import com.seckill.utils.ValidatorUtil;
import com.seckill.vo.LoginVO;
import com.seckill.vo.RespBean;
import com.seckill.vo.RespBeanEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public RespBean doLogin(LoginVO loginVO){
        String mobile= loginVO.getMobile();
        String password = loginVO.getPassword();
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }

        if(!ValidatorUtil.isMobile(mobile)){
            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
        }


        User user = userMapper.selectById(mobile);

        if(user ==null){
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }

        if(!MD5Util.fromPassToDBPass(password,user.getSalt()).equals(user.getPassword())){
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }

        return RespBean.success();

    }
}
