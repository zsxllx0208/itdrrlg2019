package com.itdr.controller;


import com.itdr.common.ServiceResponse;
import com.itdr.pojo.Products;
import com.itdr.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/product")
public class ProductsController {

    @Autowired
    ProductsService productsService;

    //获取商品分类信息
    @RequestMapping("/topcategory.do")
    public ServiceResponse<Products> topcategory(Integer sid) {


        return productsService.topcategory(sid);
    }

    //获取商品详情
    @RequestMapping("/detail.do")
    public ServiceResponse<Products> detail(
            Integer productId,
            @RequestParam(value = "is_new", required = false, defaultValue = "0") Integer is_new,
            @RequestParam(value = "is_hot", required = false, defaultValue = "0") Integer is_hot,
            @RequestParam(value = "is_banner", required = false, defaultValue = "0") Integer is_banner) {


        return productsService.detail(productId,is_new,is_hot,is_banner);
    }

    //产品搜索及动态排序
    @RequestMapping("/list.do")
    public ServiceResponse<Products> getList(
            Integer productId, String keyWord,
            @RequestParam(value = "is_new", required = false, defaultValue = "1")Integer pageNum,
            @RequestParam(value = "is_new", required = false, defaultValue = "10")Integer pageSize,
            @RequestParam(value = "is_new", required = false, defaultValue = "")String orderBy) {

        return productsService.getList(productId,keyWord,pageNum,pageSize,orderBy);
    }
}
