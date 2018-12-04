package com.zws.order.server.service.impl;

import com.netflix.discovery.converters.Auto;
import com.zws.order.common.dto.OrderDetailDto;
import com.zws.order.common.dto.OrderDto;
import com.zws.order.common.vo.OrderVO;
import com.zws.order.server.service.OrderService;
import com.zws.product.client.ProductClient;
import com.zws.product.common.vo.ProductInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/4
 */
@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private ProductClient productClient;

    @Override
    public OrderVO create(OrderDto orderDto) {
        List<String> productIds = orderDto.getItems().stream().map(OrderDetailDto::getProductId).collect(Collectors.toList());
        List<ProductInfoVO> productInfoVOList =  productClient.findProductInfoByProductIdIn(productIds);

        return null;
    }
}
