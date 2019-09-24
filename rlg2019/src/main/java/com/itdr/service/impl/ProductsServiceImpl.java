package com.itdr.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itdr.pojo.Products;
import com.itdr.common.ServiceResponse;
import com.itdr.mappers.CategoryMapper;
import com.itdr.mappers.ProductsMapper;
import com.itdr.pojo.Category;
import com.itdr.pojo.vo.ProductVO;
import com.itdr.service.ProductsService;
import com.itdr.utils.PoToVoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {
    @Autowired
    ProductsMapper productsMapper;
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public ServiceResponse<Products> topcategory(Integer pid) {
        if (pid == null || pid < 0) {
            return ServiceResponse.defeatedSR(101, "非法的参数");
        }

        List<Category> list = categoryMapper.getAll(pid);

        if (list == null) {
            return ServiceResponse.defeatedSR(103, "查询的ID不存在");
        }
        if (list.size() == 0) {
            return ServiceResponse.defeatedSR(103, "没有子分类");
        }
        return ServiceResponse.successSR(0, list);
    }

    //获取商品详情
    @Override
    public ServiceResponse<Products> detail(
            Integer productId, Integer is_new, Integer is_hot, Integer is_banner) {
        if (productId == null || productId < 0) {
            return ServiceResponse.defeatedSR(101, "非法的参数");
        }
        Products product = productsMapper.selectByProductId(productId, is_new, is_hot, is_banner);
        if (product == null) {
            return ServiceResponse.defeatedSR(103, "商品不存在");
        }
        ProductVO pro = null;
        try {
            pro = PoToVoUtil.ProductToProductVO(product);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ServiceResponse.successSR(0, pro);
    }

    //产品搜索及动态排序
    @Override
    public ServiceResponse<Products> getList(
            Integer productId, String keyWord, Integer pageNum, Integer pageSize, String orderBy) {
        if ((productId == null || productId < 0) && (keyWord == null || "".equals(keyWord))) {
            return ServiceResponse.defeatedSR(101, "参数不能为空");
        }
        //分割排序参数orderBy
        String[] s = new String[2];
        if (!orderBy.equals("")) {
            s = orderBy.split("_");
        }

        String keys = "%" + keyWord + "%";
      /*  //两种排序方式
        PageHelper.startPage(pageNum, pageSize, s[0] + " " + s[1]);*/
        PageHelper.startPage(pageNum, pageSize);
        List<Products> li = productsMapper.selectByIdOrName(productId, keys, s[0], s[1]);
        PageInfo pageInfo = new PageInfo(li);

        return ServiceResponse.successSR(0, pageInfo);
    }
}
