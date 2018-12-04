package com.zws.product.server.service;

import com.zws.product.common.vo.ProductInfoVO;
import com.zws.product.server.po.ProductInfo;

import java.util.List;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/4
 */
public interface ProductInfoService {

    List<ProductInfoVO> findProductInfoByProductIdIn(List<String> productIds);
}
