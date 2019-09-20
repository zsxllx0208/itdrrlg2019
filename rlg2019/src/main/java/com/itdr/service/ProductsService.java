package com.itdr.service;

import com.itdr.pojo.Products;
import com.itdr.common.ServiceResponse;

import java.io.IOException;

public interface ProductsService {

    ServiceResponse<Products> topcategory(Integer pid);
    //获取商品详情
    ServiceResponse<Products> detail(Integer productId, Integer is_new, Integer is_hot, Integer is_banner) throws IOException;

}
