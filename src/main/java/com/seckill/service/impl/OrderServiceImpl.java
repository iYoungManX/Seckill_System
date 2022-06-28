package com.seckill.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seckill.exception.GlobalException;
import com.seckill.mapper.OrderMapper;
import com.seckill.pojo.Order;
import com.seckill.pojo.SeckillGoods;
import com.seckill.pojo.SeckillOrder;
import com.seckill.pojo.User;
import com.seckill.service.IGoodsService;
import com.seckill.service.IOrderService;
import com.seckill.service.ISeckillGoodsService;
import com.seckill.service.ISeckillOrderService;
import com.seckill.utils.JsonUtil;
import com.seckill.utils.MD5Util;
import com.seckill.utils.UUIDUtil;
import com.seckill.vo.GoodsVo;
import com.seckill.vo.OrderDetailVo;
import com.seckill.vo.RespBeanEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author iYoungMan
 * @since 2022-06-21
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Autowired
    private ISeckillGoodsService seckillGoodsService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ISeckillOrderService seckillOrderService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IGoodsService goodsService;

    @Transactional
    @Override
    public Order seckill(User user, GoodsVo goods) {
        ValueOperations valueOperations=redisTemplate.opsForValue();
        SeckillGoods seckillGoods = seckillGoodsService.getOne(new QueryWrapper<SeckillGoods>().eq("goods_id", goods.getId()));
        seckillGoods.setStockCount(seckillGoods.getStockCount()-1);
        boolean result = seckillGoodsService.update(new UpdateWrapper<SeckillGoods>()
                .setSql("stock_count = stock_count-1").eq("goods_id",goods.getId()).gt("stock_count",0));

        if(seckillGoods.getStockCount()<1){
            valueOperations.set("isStockEmpty:" + goods.getId(), "0");
            return null;
        }

        Order order = new Order();
        order.setUserId(user.getId());
        order.setGoodsId(goods.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsName(goods.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(seckillGoods.getSeckillPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreateDate(new Date());
        orderMapper.insert(order);
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setUserId(user.getId());
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setGoodsId(goods.getId());
        seckillOrderService.save(seckillOrder);

        valueOperations.set("order:" + user.getId() + ":" + goods.getId(),
                JsonUtil.object2JsonStr(seckillOrder));
        return order;
    }

    @Override
    public OrderDetailVo detail(Long orderId) {
        if (null==orderId){
            throw new GlobalException(RespBeanEnum.ORDER_NOT_EXIST);
        }
        Order order = orderMapper.selectById(orderId);
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(order.getGoodsId());
        OrderDetailVo detail = new OrderDetailVo();
        detail.setGoodsVo(goodsVo);
        detail.setOrder(order);
        return detail;
    }

    @Override
    public String createPath(User user, Long goodsId) {
        String str = MD5Util.md5(UUIDUtil.uuid() + "123456");
        redisTemplate.opsForValue().set("seckillPath:" + user.getId() + ":" +
                goodsId, str, 60, TimeUnit.SECONDS);
        return str;
    }

    @Override
    public boolean checkPath(User user, Long goodsId, String path) {
        if (user==null|| StringUtils.isEmpty(path)){
            return false;
        }
        String redisPath = (String) redisTemplate.opsForValue().get("seckillPath:" +
                user.getId() + ":" + goodsId);
        return path.equals(redisPath);
    }


    @Override
    public boolean checkCaptcha(User user, Long goodsId, String captcha) {
        if (StringUtils.isEmpty(captcha)||null==user||goodsId<0){

            return false;
        }
        String redisCaptcha = (String) redisTemplate.opsForValue().get("captcha:" +
                user.getId() + ":" + goodsId);
        return redisCaptcha.equals(captcha);
    }
}

