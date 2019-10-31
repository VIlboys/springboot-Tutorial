package com.bjq.ticket.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * Created by CHEN on 2019/7/18.
 */
@Component//加载Spring的容器中
@Service//将服务发布出去
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "《厉害了，还出不来》";
    }
}
