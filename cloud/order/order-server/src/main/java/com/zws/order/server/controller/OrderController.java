package com.zws.order.server.controller;

import com.zws.order.common.dto.OrderMasterDTO;
import com.zws.order.common.vo.OrderVO;
import com.zws.order.server.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/26
 */
@RestController
@RequestMapping("/order")
@Api(value="/order", tags="订单模块")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 1. 参数检验
     * 2. 查询商品信息(调用商品服务)
     * 3. 计算总价
     * 4. 扣库存(调用商品服务)
     * 5. 订单入库
     */
     @ApiOperation("创建订单")
     @PostMapping("/create")
     public OrderVO create(@Valid OrderMasterDTO orderMasterDTO, BindingResult bindingResult){
         return orderService.create(orderMasterDTO);
     }

}
