package com.example.web;

import com.example.mq.RabbitMQConfig;
import com.sun.javaws.Main;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProducterController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/message")
    public Object sendMessage(){
        new Thread(() -> {
            for (int i = 0 ;i < 100 ; i++){
//                String value = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
//                Console.log("send message {}",value);
//                rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,RabbitMQConfig.ROUTING_KEY,value);
            }
        }).start();
        return "ok";
    }

}
