package com.seckill.rabiitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

//    public void send(Object msg){
//        log.info("sending message: " +msg);
//        rabbitTemplate.convertAndSend("fanoutExchange","",msg);
//    }
//
//    public void send01(Object msg){
//        log.info("sending red message: "+ msg);
//        rabbitTemplate.convertAndSend("directExchange","queue.red",msg);
//    }
//
//    public void send02(Object msg){
//        log.info("sending green message: "+ msg);
//        rabbitTemplate.convertAndSend("directExchange","queue.green",msg);
//    }
//
//
//    public void send03(Object msg){
//        log.info("sending (queue01) message: "+ msg);
//        rabbitTemplate.convertAndSend("topicExchange","queue.red.message",msg);
//    }
//
//
//    public void send04(Object msg){
//        log.info("sending (queue01+queue02) message: "+ msg);
//        rabbitTemplate.convertAndSend("topicExchange","message.queue.green.abc",msg);
//    }
//
//
//    public void send05(String msg){
//        log.info("sending (queue01+queue02) message: "+ msg);
//        MessageProperties properties = new MessageProperties();
//        properties.setHeader("color","red");
//        properties.setHeader("speed","fast");
//        Message message= new Message(msg.getBytes(),properties);
//        rabbitTemplate.convertAndSend("headersExchange","",message);
//    }
//
//
//    public void send06(String msg){
//        log.info("sending (queue01) message: "+ msg);
//        MessageProperties properties = new MessageProperties();
//        properties.setHeader("color","red");
//        properties.setHeader("speed","normal");
//        Message message= new Message(msg.getBytes(),properties);
//        rabbitTemplate.convertAndSend("headersExchange","",message);
//    }

    public void sendSeckillMessage(String message){
        log.info("sending message: "+message);
        rabbitTemplate.convertAndSend("seckillExchange","seckill.message",message);
    }


}
