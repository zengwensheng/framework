package com.zws.order.server.dao;

import com.zws.order.server.po.OrderDetail;
import com.zws.order.server.po.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/10
 */
public interface OrderMasterDao extends JpaRepository<OrderMaster,String> {

}
