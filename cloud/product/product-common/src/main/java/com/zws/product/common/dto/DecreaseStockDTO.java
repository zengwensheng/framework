package com.zws.product.common.dto;

import lombok.Data;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/10
 */
@Data
public class DecreaseStockDTO {
    private String productId;

    private Integer productQuantity;

    public DecreaseStockDTO() {
    }

    public DecreaseStockDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
