package com.itdr.controller;


import com.itdr.common.ServiceResponse;
import com.itdr.pojo.Cart;
import com.itdr.pojo.Users;
import com.itdr.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    //购物车添加商品
    @RequestMapping("/add.do")
    public ServiceResponse<Cart> addOne(Integer productId, Integer count, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return ServiceResponse.defeatedSR(100, "用户未登录");
        } else {
            return cartService.addOne(productId, count, user.getId());
        }

    }

    //获取当前用户购物车列表
    @RequestMapping("/list.do")
    public ServiceResponse<Cart> listCart(Integer productId, Integer count, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return ServiceResponse.defeatedSR(100, "用户未登录");
        } else {
            return cartService.listCart(user.getId());
        }

    }

    //更新购物车某个产品数量
    @RequestMapping("/update.do")
    public ServiceResponse<Cart> updateCart(Integer productId, Integer count, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return ServiceResponse.defeatedSR(100, "用户未登录");
        } else {
            return cartService.updateCart(productId, count, user.getId());
        }

    }

    //移除购物车某个产品
    @RequestMapping("/delete_product.do")
    public ServiceResponse<Cart> delete_product(String productIds, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return ServiceResponse.defeatedSR(100, "用户未登录");
        } else {
            return cartService.delete_product(productIds, user.getId());
        }

    }

    //查询在购物车里的信息条数
    @RequestMapping("/get_cart_product_count.do")
    public ServiceResponse<Cart> get_cart_product_count(HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return ServiceResponse.defeatedSR(100, "用户未登录");
        } else {
            return cartService.get_cart_product_count(user.getId());
        }

    }

}
