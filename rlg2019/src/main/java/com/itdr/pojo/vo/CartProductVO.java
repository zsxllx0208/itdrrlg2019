package com.itdr.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartProductVO {
    private Integer id;

    private Integer userId;

    private Integer productId;

    private Integer quantity;

    private String name;

    private String subtitle;

    private String mainImage;

    private BigDecimal price;

    private Integer stock;

    private Integer status;

    private BigDecimal productTotalPrice;

    private Integer productChecked;

    private String limitQuantity;


}
