package com.zws.order.server.service;

import com.zws.order.common.dto.OrderMasterDTO;
import com.zws.order.common.vo.OrderVO;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/4
 */
public interface OrderService {

    OrderVO create(OrderMasterDTO orderMasterDTO);
}
