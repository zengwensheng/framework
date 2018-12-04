package com.zws.product.server.dao;

import com.zws.product.server.po.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/4
 */
public interface ProductInfoDao extends JpaRepository<ProductInfo,String> {

    List<ProductInfo>  findProductInfoByProductIdIn(List<String> productIds);
}
