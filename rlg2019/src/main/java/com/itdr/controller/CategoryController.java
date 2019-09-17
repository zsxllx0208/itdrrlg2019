package com.itdr.controller;

import com.itdr.common.ServiceResponse;
import com.itdr.pojo.Category;
import com.itdr.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/manage/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //获取品类子节点(平级)
    @RequestMapping("/get_category.do")
    public ServiceResponse<Category> getCategory(Integer categoryId)
    {
        ServiceResponse sr =categoryService.selectBycategoryId(categoryId);

        return sr;
    }

    //增加节点
    @RequestMapping("/add_category.do")
    private ServiceResponse<Category> addCategory(Category category) {

        ServiceResponse sr = categoryService.addCategory(category);

        return sr;
    }
}
