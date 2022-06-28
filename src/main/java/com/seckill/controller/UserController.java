package com.seckill.controller;


import com.seckill.pojo.User;
import com.seckill.rabiitmq.MQSender;
import com.seckill.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author iYoungMan
 * @since 2022-06-20
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    public MQSender mqSender;

    @RequestMapping("/info")
    @ResponseBody
    public RespBean info(User user){
        return RespBean.success(user);
    }

//    @RequestMapping("/mq")
//    @ResponseBody
//    public void mq(){
//        mqSender.send("Hello");
//    }
//
//
//    @RequestMapping("/mq/fanout")
//    @ResponseBody
//    public void mq01(){
//        mqSender.send("Hello");
//    }
//
//    @RequestMapping("/mq/direct01")
//    @ResponseBody
//    public void mq02(){
//        mqSender.send01("Hello,red");
//    }
//
//
//    @RequestMapping("/mq/direct02")
//    @ResponseBody
//    public void mq03(){
//        mqSender.send02("Hello,green");
//    }
//
//
//
//    @RequestMapping("/mq/topic01")
//    @ResponseBody
//    public void mq04(){
//        mqSender.send03("Hello,red");
//    }
//
//
//    @RequestMapping("/mq/topic02")
//    @ResponseBody
//    public void mq05(){
//        mqSender.send04("Hello,green");
//    }
//
//
//    @RequestMapping("/mq/header01")
//    @ResponseBody
//    public void mq06(){
//        mqSender.send05("Hello,header01");
//    }
//
//
//    @RequestMapping("/mq/header02")
//    @ResponseBody
//    public void mq07(){
//        mqSender.send06("Hello,header02");
//    }

}
