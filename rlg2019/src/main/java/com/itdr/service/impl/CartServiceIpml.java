package com.itdr.service.impl;

import com.itdr.common.ServiceResponse;
import com.itdr.mappers.CartMapper;
import com.itdr.mappers.ProductsMapper;
import com.itdr.pojo.Cart;
import com.itdr.pojo.Products;
import com.itdr.pojo.vo.CartProductVO;
import com.itdr.pojo.vo.CartVO;
import com.itdr.service.CartService;
import com.itdr.utils.BigDecimalUtils;
import com.itdr.utils.PoToVoUtil;
import com.itdr.utils.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CartServiceIpml implements CartService {

    @Autowired
    CartMapper cartMapper;

    @Autowired
    ProductsMapper productsMapper;

    @Override
    public ServiceResponse<Cart> addOne(Integer productId, Integer count, Integer uid) {
        if (productId == null || productId <= 0 || count == null || count <= 0) {
            return ServiceResponse.defeatedSR(101, "非法参数");
        }
        //封装购物车对象
        Cart cart = new Cart();
        cart.setUserId(uid);
        cart.setProductId(productId);
        cart.setQuantity(count);
        cart.setChecked(1);
        //判断数据库中是否已存在相同购物信息
        Cart cart1 = cartMapper.selectByUidAndPid(uid, productId);
        if (cart1 != null) {
            //存在更新数据
            cart1.setQuantity(cart1.getQuantity() + count);
            cartMapper.updateByPrimaryKeySelective(cart1);
        } else {
            //不存在添加数据
            int insert = cartMapper.insert(cart);
        }
        return this.listCart(uid);
    }

    private CartVO getCartVO(Integer uid) {
        //根据用户ID查询购物车数据
        List<Cart> cartList = cartMapper.selectByUid(uid);
        List<CartProductVO> cplist = new ArrayList<>();
        CartVO cartVO = new CartVO();

        //创建变量存储购物车总价
        BigDecimal cartTotalPrice = new BigDecimal("0");
        //查询购物车中商品的详细信息
        if (cartList.size() != 0) {
            for (Cart cart : cartList) {
                Products products = productsMapper.selectByProductId(cart.getProductId(), 0, 0, 0);
                CartProductVO cartProductVO = PoToVoUtil.getOne(cart, products);
                //更新有效库存
                Cart cartForQuantity = new Cart();
                cartForQuantity.setId(cart.getId());
                cartForQuantity.setQuantity(cart.getQuantity());
                cartMapper.updateByPrimaryKeySelective(cartForQuantity);

                if (cart.getChecked() == 1) {
                    //累积购物车总价
                    cartTotalPrice = BigDecimalUtils.add(cartTotalPrice.doubleValue(), cartProductVO.getProductTotalPrice().doubleValue());
                }
                cplist.add(cartProductVO);
            }
        } else {

        }
        //封装cartVO数据
        cartVO.setCartProductVOList(cplist);
        cartVO.setAllChecked(this.checkAll(uid));
        cartVO.setCartTotalPrice(cartTotalPrice);
        try {
            cartVO.setImageHost(PropertiesUtil.getValue("imageHost"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cartVO;
    }

    private boolean checkAll(Integer uid) {
        int row = cartMapper.selectByUidCheck(uid);
        if (row == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ServiceResponse<Cart> listCart(Integer uid) {
        CartVO cartVO = this.getCartVO(uid);
        return ServiceResponse.successSR(0, cartVO);
    }

    //更新购物车某个产品数量
    @Override
    public ServiceResponse<Cart> updateCart(Integer productId, Integer count,Integer uid) {
        if (productId == null || productId <= 0 || count == null || count <= 0) {
            return ServiceResponse.defeatedSR(101, "非法参数");
        }
        Cart cart = cartMapper.selectByUidAndPid(uid, productId);
        cart.setQuantity(count);
        int row = cartMapper.updateByPrimaryKeySelective(cart);
        return this.listCart(uid);
    }

    //移除购物车某个产品
    @Override
    public ServiceResponse<Cart> delete_product(String productIds, Integer uid) {
        if (productIds==null||productIds.equals("")){
            return ServiceResponse.defeatedSR(101,"非法参数");
        }
        String[] split = productIds.split(",");
        List<String> strings = Arrays.asList(split);
        int row = cartMapper.deleteByProductIds(strings,uid);
        return null;
    }

    //查询在购物车里的信息条数
    @Override
    public ServiceResponse<Cart> get_cart_product_count(Integer id) {
        List<Cart> cartList = cartMapper.selectByUid(id);

        return ServiceResponse.successSR(0,cartList.size());
    }


}
