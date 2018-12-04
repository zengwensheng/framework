package com.zws.order.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel("购物车商品信息")
public class OrderDetailDto {

    @ApiModelProperty(value = "商品id")
    @NotEmpty(message = "商品id不能为空")
    private String productId;

}
