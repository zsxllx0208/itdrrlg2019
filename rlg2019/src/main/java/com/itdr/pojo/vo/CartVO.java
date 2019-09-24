package com.itdr.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class CartVO {
    private List<CartProductVO> cartProductVOList;
    private boolean allChecked;
    private BigDecimal cartTotalPrice;
    private String imageHost;
}
