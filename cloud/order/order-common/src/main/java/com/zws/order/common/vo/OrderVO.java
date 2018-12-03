package com.zws.order.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/3
 */
@ApiModel(description = "订单返回对象")
@Data
public class OrderVO {

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "买家名字")
    private String buyerName;

    @ApiModelProperty(value = "买家手机号")
    private String buyerPhone;

    @ApiModelProperty(value = "买家地址")
    private String buyerAddress;

    @ApiModelProperty(value = "买家微信Openid")
    private String buyerOpenid;

    @ApiModelProperty(value = "订单总金额")
    private BigDecimal orderAmount;

    @ApiModelProperty(value = "订单状态, 默认为0新下单")
    private Integer orderStatus;

    @ApiModelProperty(value = "支付状态, 默认为0未支付")
    private Integer payStatus;

   //private List<OrderDetail> orderDetailList;
}
