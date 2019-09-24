package com.itdr.service;

import com.itdr.common.ServiceResponse;
import com.itdr.pojo.Cart;

public interface CartService {
    ServiceResponse<Cart> addOne(Integer productId, Integer count,Integer uid);

    ServiceResponse<Cart> listCart(Integer id);
    //更新购物车某个产品数量
    ServiceResponse<Cart> updateCart(Integer productId, Integer count,Integer uid);
    //移除购物车某个产品
    ServiceResponse<Cart> delete_product(String productIds, Integer id);
    //查询在购物车里的信息条数
    ServiceResponse<Cart> get_cart_product_count(Integer id);

}
