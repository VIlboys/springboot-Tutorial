package com.bjq.amqp;

import com.bjq.amqp.bean.Book;
import javafx.scene.layout.Background;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.print.DocFlavor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot02AmqpApplicationTests {

    //注入RabbitTemplate
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void createExchange()
    {
        //new一个Direct的Exchange
        //amqpAdmin.declareExchange(new DirectExchange("amqpbjq.news"));

        //创建Queue
        //amqpAdmin.declareQueue(new Queue("amqpbjq.queue",true));

        amqpAdmin.declareBinding(new Binding("amqpbjq.queue",Binding.DestinationType.QUEUE,"amqpbjq.news","amqu.haha",null));
        System.out.println("绑定完成");
    }



    /**
     * 测试:单播（点对点）
     */
    @Test
    public void contextLoads() {
        /**
         * message需要自己构造
         */
        //rabbitTemplate.send(exchage,routeKey,message);
        //Object默认被当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq
        //rabbitTemplate.convertAndSend(exchange,routeKey,Object);
        Map<String,Object> map = new HashMap<>();
        map.put("msg","这是第一个测试消息");
        map.put("data", Arrays.asList("HelloWorld",123,true));
        //对象被默认序列化后发送出去
        //rabbitTemplate.convertAndSend("exchange.direct","bjq.news",map);
        //发布内容传入一个对象
        rabbitTemplate.convertAndSend("exchange.direct","bjq.news",new Book("天才在左","高铭"));
    }

    //接受数据，如何将数据自动的转换为json格式
    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("bjq.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    /**
     * 广播
     */
    @Test
    public void RabbitmqFanout()
    {
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("Spring第四版","张卫彬"));
    }

}
