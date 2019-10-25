package com.bjq.amqp.service;

import com.bjq.amqp.bean.Book;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    /**
     * @RabbitListener用来监听消息队列里面得内容
     * @param book
     */
    @RabbitListener(queues = "bjq")
    public void listening(Book book)
    {
        System.out.println("收到消息"+book);
    }

}
