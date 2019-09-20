package com.itdr.service;

import com.itdr.common.ServiceResponse;
import com.itdr.pojo.Category;

public interface CategoryService {
    //获取品类子节点(平级)
    ServiceResponse selectBycategoryId(Integer cid);
    //增加节点
    ServiceResponse addCategory(Category category);
    //获取当前分类id及递归子节点categoryId
    ServiceResponse getDeepCategory(Integer categoryId);
}
