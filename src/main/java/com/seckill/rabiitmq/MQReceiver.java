package com.seckill.rabiitmq;

import com.seckill.pojo.Goods;
import com.seckill.pojo.SeckillMessage;
import com.seckill.pojo.SeckillOrder;
import com.seckill.pojo.User;
import com.seckill.service.IGoodsService;
import com.seckill.service.IOrderService;
import com.seckill.utils.JsonUtil;
import com.seckill.vo.GoodsVo;
import com.seckill.vo.RespBean;
import com.seckill.vo.RespBeanEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQReceiver {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IOrderService orderService;

    @RabbitListener(queues = "seckillQueue")
    public void receive(String message){
        log.info("received message object: "+message);
        SeckillMessage seckillMessage= JsonUtil.jsonStr2Object(message,SeckillMessage.class);
        long goodId=seckillMessage.getGoodId();
        User user= seckillMessage.getUser();
        GoodsVo goodsVo= goodsService.findGoodsVoByGoodsId(goodId);
        if(goodsVo.getStockCount()<1){
            return;
        }
        SeckillOrder seckillOrder=(SeckillOrder) redisTemplate.opsForValue().get("order:"+user.getId()+":"+goodId);

        if(seckillOrder!=null){
            return;
        }

        orderService.seckill(user,goodsVo);

    }




}
