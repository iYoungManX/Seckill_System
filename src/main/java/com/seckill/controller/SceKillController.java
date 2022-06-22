package com.seckill.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.seckill.pojo.Order;
import com.seckill.pojo.SeckillOrder;
import com.seckill.pojo.User;
import com.seckill.service.IGoodsService;
import com.seckill.service.IOrderService;
import com.seckill.service.ISeckillOrderService;
import com.seckill.vo.GoodsVo;
import com.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seckill")
public class SceKillController {
    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private ISeckillOrderService seckillOrderService;

    @Autowired
    private IOrderService orderService;


    @RequestMapping("/doSeckill")
    public String doSeckill(Model model, User user, long goodsId){
        if(user ==null){
            return "login";
        }
        model.addAttribute("user",user);
        GoodsVo goods= goodsService.findGoodsVoByGoodsId(goodsId);
        if(goods.getStockCount()<1){
            model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK);
            return "secKillFail";
        }

        SeckillOrder seckillOrder = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>()
                .eq("user_id", user.getId())
                .eq("goods_id", goodsId));
        if(seckillOrder!=null){
            model.addAttribute("errmsg", RespBeanEnum.REPEAT_ERROR);
            return "secKillFail";
        }
        Order order= orderService.seckill(user,goods);
        model.addAttribute("order",order);
        model.addAttribute("goods",goods);
        return "orderDetail";

    }
}
