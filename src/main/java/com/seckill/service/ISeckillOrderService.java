package com.seckill.service;

import com.seckill.pojo.SeckillOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seckill.pojo.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author iYoungMan
 * @since 2022-06-21
 */
public interface ISeckillOrderService extends IService<SeckillOrder> {

    Long getResult(User user, Long goodsId);
}
