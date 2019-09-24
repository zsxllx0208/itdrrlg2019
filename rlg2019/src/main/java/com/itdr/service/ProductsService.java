package com.itdr.service;

import com.itdr.pojo.Products;
import com.itdr.common.ServiceResponse;



public interface ProductsService {
    //查询当前品类ID的子类ID
    ServiceResponse<Products> topcategory(Integer pid);

    //获取商品详情
    ServiceResponse<Products> detail(Integer productId, Integer is_new, Integer is_hot, Integer is_banner);

    //产品搜索及动态排序
    ServiceResponse<Products> getList(Integer productId, String keyWord, Integer pageNum, Integer pageSize, String orderBy);



}
