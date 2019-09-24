package com.itdr.service.impl;

import com.itdr.common.ServiceResponse;
import com.itdr.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public ServiceResponse createOrder(Integer shippingId, Integer id) {
        if (shippingId==null||shippingId<0){
            return ServiceResponse.defeatedSR(101,"非法参数");
        }
        return null;
    }
}
