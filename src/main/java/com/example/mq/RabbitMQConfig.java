package com.example.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    public final static String QUEUE_NAME = "spring-boot-queue";
    public final static String EXCHANGE_NAME = "spring-boot-exchange";
    public final static String ROUTING_KEY = "spring-boot-key";

    //创建队列
    @Bean
    public  Queue queue(){
        return new Queue(QUEUE_NAME);
    }

    //创建一个topic类型的exchange
    @Bean
    public  TopicExchange exchange(){
        return new TopicExchange(EXCHANGE_NAME);
    }

    //使用routing_key把queue_name绑定到exchange_name上
    @Bean
    public  Binding binding(Queue queue,TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    //绑定rabbitmq账号,创建连接工厂
    @Bean
    public  ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost",5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    //获取rabbitmq发送模板
    @Bean
    public  RabbitTemplate template(){
        return new RabbitTemplate(connectionFactory());
    }
}
