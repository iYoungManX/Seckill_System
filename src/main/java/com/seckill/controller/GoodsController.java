package com.seckill.controller;

import com.seckill.pojo.User;
import com.seckill.service.IGoodsService;
import com.seckill.service.IUserService;
import com.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IGoodsService goodsService;

    @RequestMapping("/toList")
    public String toList( Model model, User user){
        model.addAttribute("user",user);
        model.addAttribute("goodsList",goodsService.findGoodVo());
        return "goodsList";
    }

    @RequestMapping("/toDetail/{goodsId}")
    public String toDetail(Model model, User user,@PathVariable long goodsId){
        model.addAttribute("user",user);
        GoodsVo goodsVo= goodsService.findGoodsVoByGoodsId(goodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date nowDate= new Date();
        int secKillStatus=0;
        int remainSeconds=0;
        if(nowDate.before(startDate)){
            remainSeconds= (int) ((startDate.getTime()-nowDate.getTime())/1000);
        }else if(nowDate.after(endDate)){
            secKillStatus=2;
            remainSeconds=-1;
        }else {
            remainSeconds=0;
            secKillStatus=1;
        }

        model.addAttribute("remainSeconds",remainSeconds);
        model.addAttribute("secKillStatus",secKillStatus);
        model.addAttribute("goods",goodsVo);
        return "goodsDetail";
    }
}
