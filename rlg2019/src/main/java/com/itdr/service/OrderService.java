package com.itdr.service;

import com.itdr.common.ServiceResponse;

public interface OrderService {
    //创建订单
    ServiceResponse createOrder(Integer shippingId, Integer id);
}
