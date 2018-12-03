package com.zws.order.common.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/26
 */
@ApiModel(description = "订单创建传输对象")
@Data
public class OrderDto {

    @ApiModelProperty(value = "买家姓名")
    @NotEmpty(message = "买家姓名必填")
    private String name;

    @ApiModelProperty(value = "买家手机号")
    @NotEmpty(message = "买家手机号必填")
    private String phone;


    @ApiModelProperty(value = "买家地址")
    @NotEmpty(message = "买家地址必填")
    private String address;


    @ApiModelProperty(value = "买家微信openid")
    @NotEmpty(message = "买家微信openid必填")
    private String openid;


    @ApiModelProperty(value = "购物车")
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
