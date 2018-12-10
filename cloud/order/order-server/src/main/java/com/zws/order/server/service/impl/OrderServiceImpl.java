package com.zws.order.server.service.impl;

import com.zws.order.common.dto.OrderDetailDTO;
import com.zws.order.common.dto.OrderMasterDTO;
import com.zws.order.common.enums.OrderStatusEnum;
import com.zws.order.common.enums.PayStatusEnum;
import com.zws.order.common.vo.OrderVO;
import com.zws.order.server.dao.OrderDetailDao;
import com.zws.order.server.dao.OrderMasterDao;
import com.zws.order.server.po.OrderDetail;
import com.zws.order.server.po.OrderMaster;
import com.zws.order.server.service.OrderService;
import com.zws.product.client.ProductClient;
import com.zws.product.common.dto.DecreaseStockDTO;
import com.zws.product.common.vo.ProductInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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

    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private OrderMasterDao orderMasterDao;

    @Override
    public OrderVO create(OrderMasterDTO orderMasterDTO) {
        String orderId = UUID.randomUUID().toString();
        List<String> productIds = orderMasterDTO.getItems().stream().map(OrderDetailDTO::getProductId).collect(Collectors.toList());
        List<ProductInfoVO> productInfoVOList = productClient.findProductInfoByProductIdIn(productIds);

        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        for (OrderDetailDTO orderDetailDTO : orderMasterDTO.getItems()) {
            for (ProductInfoVO productInfoVO : productInfoVOList) {
                if (orderDetailDTO.getProductId().equals(productInfoVO.getProductId())) {

                    orderAmount = productInfoVO.getProductPrice().multiply(new BigDecimal(orderDetailDTO.getProductQuantity())).add(orderAmount);
                    OrderDetail orderDetail = new OrderDetail();
                    BeanUtils.copyProperties(productInfoVO,orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(UUID.randomUUID().toString());
                    orderDetail.setProductQuantity(orderDetailDTO.getProductQuantity());

                    orderDetailDao.save(orderDetail);
                }
            }
        }

        List<DecreaseStockDTO> decreaseStockDTOList = orderMasterDTO.getItems()
                .stream()
                .map(orderDetailDTO -> new DecreaseStockDTO(orderDetailDTO.getProductId(),orderDetailDTO.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockDTOList);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderMasterDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterDao.save(orderMaster);

        OrderVO vo;
        BeanUtils.copyProperties(orderMaster,vo = new OrderVO());
        return vo;


    }
}
