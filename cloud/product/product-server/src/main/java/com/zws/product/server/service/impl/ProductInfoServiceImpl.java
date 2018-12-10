package com.zws.product.server.service.impl;

import com.zws.product.common.dto.DecreaseStockDTO;
import com.zws.product.common.vo.ProductInfoVO;
import com.zws.product.server.dao.ProductInfoDao;
import com.zws.product.server.exception.ProductEnum;
import com.zws.product.server.exception.ProductException;
import com.zws.product.server.po.ProductInfo;
import com.zws.product.server.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    @Transactional
    @Override
    public void decreaseStock(List<DecreaseStockDTO> decreaseStockDTOList) {
        for (DecreaseStockDTO decreaseStockInput: decreaseStockDTOList) {
            Optional<ProductInfo> productInfoOptional = productInfoDao.findById(decreaseStockInput.getProductId());
            //判断商品是否存在
            if (!productInfoOptional.isPresent()){
                throw new ProductException(ProductEnum.PRODUCT_NOT_EXIST);
            }

            ProductInfo productInfo = productInfoOptional.get();
            //库存是否足够
            Integer result = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ProductEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productInfoDao.save(productInfo);
        }
    }
}
