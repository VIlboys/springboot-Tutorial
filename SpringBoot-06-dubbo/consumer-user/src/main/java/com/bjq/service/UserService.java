package com.bjq.service;

import com.bjq.ticker.service.TicketService;
import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Reference;

@Service
public class UserService {
    @Reference
    TicketService ticketService;

    public void hello(){
        String ticket = ticketService.getTicket();
        System.out.printf("买到了"+ticket);
    }
}
