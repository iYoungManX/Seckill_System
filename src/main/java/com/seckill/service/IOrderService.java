package com.seckill.service;

import com.seckill.pojo.Order;

import com.baomidou.mybatisplus.extension.service.IService;
import com.seckill.pojo.User;
import com.seckill.vo.GoodsVo;

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
}
