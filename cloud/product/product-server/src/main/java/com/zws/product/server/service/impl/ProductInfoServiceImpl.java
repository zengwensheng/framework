package com.zws.product.server.service.impl;

import com.zws.product.common.vo.ProductInfoVO;
import com.zws.product.server.dao.ProductInfoDao;
import com.zws.product.server.po.ProductInfo;
import com.zws.product.server.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/4
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public List<ProductInfoVO> findProductInfoByProductIdIn(List<String> productIds) {

        List<ProductInfo> productInfoList = productInfoDao.findProductInfoByProductIdIn(productIds);

        return transForm(productInfoList);
    }


    private List<ProductInfoVO>  transForm(List<ProductInfo> productInfoList){
        List<ProductInfoVO> productInfoVOList = new ArrayList<>();
        if(productInfoList!=null||productInfoVOList.size()>0){
            for (ProductInfo productInfo: productInfoList) {
                ProductInfoVO productInfoVO = new ProductInfoVO();
                BeanUtils.copyProperties(productInfo,productInfoVO);
                productInfoVOList.add(productInfoVO);
            }
        }
        return productInfoVOList;
    }
}
