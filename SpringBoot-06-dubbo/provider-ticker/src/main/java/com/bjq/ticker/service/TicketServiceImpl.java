package com.bjq.ticker.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
/**
 * Created by liu on
 */
@Component
@Service
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "《厉害了,我的哥》";
    }
}
