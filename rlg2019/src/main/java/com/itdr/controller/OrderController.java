package com.itdr.controller;

import com.itdr.common.ServiceResponse;
import com.itdr.pojo.Users;
import com.itdr.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    //创建订单
    @RequestMapping("/create.do")
    public ServiceResponse createOrder(Integer shippingId , HttpSession session){
        Users user = (Users) session.getAttribute("user");
        if (user==null){
            return ServiceResponse.defeatedSR(100,"用户未登录");
        }

        return orderService.createOrder(shippingId,user.getId());
    }
}
