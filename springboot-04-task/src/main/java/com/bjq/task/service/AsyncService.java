package com.bjq.task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Async//告诉springboot这个方法是个异步方法
    public void hello() throws InterruptedException {

        Thread.sleep(3000);

        System.out.println("hello数据处理中");
    }


}
