package com.seckill.service;

import com.seckill.pojo.Order;

import com.baomidou.mybatisplus.extension.service.IService;
import com.seckill.pojo.User;
import com.seckill.vo.GoodsVo;
import com.seckill.vo.OrderDetailVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author iYoungMan
 * @since 2022-06-21
 */
public interface IOrderService extends IService<Order> {

    Order seckill(User user, GoodsVo goods);
    OrderDetailVo detail(Long orderId);

    String createPath(User user, Long goodsId);
    boolean checkPath(User user, Long goodsId, String path);

    boolean checkCaptcha(User user, Long goodsId, String captcha);
}
