package com.itdr.mappers;

import com.itdr.pojo.Cart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    Cart selectByUidAndPid(@Param("uid") Integer uid, @Param("productId") Integer productId);

    List<Cart> selectByUid(@Param("uid") Integer uid);

    //根据用户ID判断购物车是否全选
    int selectByUidCheck(@Param("uid") Integer uid);
    //移除购物车某个产品
    int deleteByProductIds(@Param("productIds") List<String> productIds,@Param("uid")  Integer uid);
}