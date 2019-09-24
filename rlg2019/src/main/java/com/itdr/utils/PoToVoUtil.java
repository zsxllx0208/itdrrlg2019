package com.itdr.utils;

import com.itdr.pojo.Cart;
import com.itdr.pojo.Products;
import com.itdr.pojo.vo.CartProductVO;
import com.itdr.pojo.vo.ProductVO;

import java.io.IOException;
import java.math.BigDecimal;

public class PoToVoUtil {
    //商品封装
    public static ProductVO ProductToProductVO(Products p) throws IOException {
        ProductVO pvo = new ProductVO();
        pvo.setImageHost(PropertiesUtil.getValue("imageHost"));
        pvo.setId(p.getId());
        pvo.setId(p.getId());
        pvo.setCategoryId(p.getCategoryId());
        pvo.setCreateTime(p.getCreateTime());
        pvo.setDetail(p.getDetail());
        pvo.setIsBanner(p.getIsBanner());
        pvo.setIsHot(p.getIsHot());
        pvo.setIsNew(p.getIsNew());
        pvo.setMainImage(p.getMainImage());
        pvo.setName(p.getName());
        pvo.setPrice(p.getPrice());
        pvo.setStatus(p.getStatus());
        pvo.setStock(p.getStock());
        pvo.setSubImages(p.getSubImages());
        pvo.setSubtitle(p.getSubtitle());
        pvo.setUpdateTime(p.getUpdateTime());

        return pvo;
    }

    //购物车封装
    public static CartProductVO getOne(Cart cart, Products p){
        //封装cartProductVo
        CartProductVO cartProductVO = new CartProductVO();
        cartProductVO.setId(cart.getId());
        cartProductVO.setUserId(cart.getUserId());
        cartProductVO.setProductId(cart.getProductId());
        cartProductVO.setProductChecked(cart.getChecked());

        //查询到的商品不能为null
        if(p != null){
            cartProductVO.setName(p.getName());
            cartProductVO.setSubtitle(p.getSubtitle());
            cartProductVO.setMainImage(p.getMainImage());
            cartProductVO.setPrice(p.getPrice());
            cartProductVO.setStock(p.getStock());
            cartProductVO.setStatus(p.getStatus());
        }

        Integer count = 0;
        //处理库存问题
        if(cart.getQuantity() <= p.getStock()){
            count = cart.getQuantity();
            cartProductVO.setLimitQuantity("LIMIT_NUM_SUCCESS");
        }else{
            count = p.getStock();
            cartProductVO.setLimitQuantity("LIMIT_NUM_FAILED");
        }
        cartProductVO.setQuantity(count);

        //计算本条购物信息总价
        BigDecimal productTotalPrice = BigDecimalUtils.mul(p.getPrice().doubleValue(),cartProductVO.getQuantity());
        cartProductVO.setProductTotalPrice(productTotalPrice);

        return cartProductVO;
    }

}
