package com.itdr.service.impl;


import com.itdr.common.ServiceResponse;
import com.itdr.mappers.CategoryMapper;
import com.itdr.pojo.Category;
import com.itdr.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    //获取品类子节点(平级)
    @Override
    public ServiceResponse selectBycategoryId(Integer categoryId) {

        if (categoryId == null) {
            return ServiceResponse.defeatedSR(101, "查询失败");
        }
        List<Category> list = categoryMapper.selectByCategoryId(categoryId);
        if (list == null) {

            return ServiceResponse.defeatedSR(103, "查询失败");
        }

        return ServiceResponse.successSR(0, list);

    }
    //增加节点
    @Override
    public ServiceResponse addCategory(Category category) {
        if (category == null) {
            return ServiceResponse.defeatedSR(101, "参数不能为空");
        }
        if (category.getName() == null || "".equals(category.getName())) {
            return ServiceResponse.defeatedSR(101, "分类名不能为空");
        }
        if (category.getParentId() == null) {
            return ServiceResponse.defeatedSR(101, "父ID不能为空");
        }
        int row = categoryMapper.insertSelective(category);
        if (row < 1) {
            return ServiceResponse.defeatedSR(101, "操作失败");
        }
        return ServiceResponse.successSR(0, null, "操作成功");
    }


}
