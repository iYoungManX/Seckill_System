package com.seckill.controller;

import com.seckill.service.IUserService;
import com.seckill.vo.LoginVO;
import com.seckill.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    private IUserService userService;



    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(@Valid LoginVO loginVO, HttpServletRequest request, HttpServletResponse response){
        //log.info("{}",loginVO);
        return userService.doLogin(loginVO,request,response);
    }

}
