package com.zws.product.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/4
 */
@Data
@ApiModel(description = "商品返回信息")
public class ProductInfoVO {

    @ApiModelProperty(value = "商品id")
    private String productId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "单价")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "库存")
    private Integer productStock;

    @ApiModelProperty(value = "描述")
    private String productDescription;

    @ApiModelProperty(value = "小图")
    private String productIcon;

    @ApiModelProperty(value = "状态, 0正常1下架")
    private Integer productStatus;

    @ApiModelProperty(value = "类目编号")
    private Integer categoryType;

}
