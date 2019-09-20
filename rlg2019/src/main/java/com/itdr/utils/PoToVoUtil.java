package com.itdr.utils;

import com.itdr.pojo.Products;
import com.itdr.pojo.vo.ProductVO;

import java.io.IOException;

public class PoToVoUtil {
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

}
