package com.zws.order.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("购物车商品信息")
public class OrderDetailDTO {

    @ApiModelProperty(value = "商品id")
    @NotEmpty(message = "商品id不能为空")
    private String productId;

    @ApiModelProperty(value = "商品数量")
    @NotNull(message = "商品数量不能为空")
    private Integer productQuantity;

}
