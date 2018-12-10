package com.zws.product.server.controller;

import com.zws.product.common.dto.DecreaseStockDTO;
import com.zws.product.common.vo.ProductInfoVO;
import com.zws.product.server.service.ProductInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/4
 */
@RestController
@RequestMapping("/product")
@Api(value="/product",tags = "商品模块")
public class ProductController {


    @Autowired
    private ProductInfoService productInfoService;

    @PostMapping("/findProductInfoByProductIdIn")
    @ApiOperation("商品查询")
    public List<ProductInfoVO> findProductInfoByProductIdIn(@RequestBody List<String> productIds){
       return productInfoService.findProductInfoByProductIdIn(productIds);
    }


    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockDTO> decreaseStockInputList) {
        productInfoService.decreaseStock(decreaseStockInputList);
    }

}
