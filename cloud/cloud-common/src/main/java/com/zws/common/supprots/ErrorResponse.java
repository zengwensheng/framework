package com.zws.common.supprots;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import lombok.Data;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/3
 */
@Data
@ApiModel(value = "错误信息")
public class ErrorResponse {

    @ApiModelProperty(value = "错误信息")
    private String msg;
    @ApiModelProperty(value = "错误编码")
    private Integer code;

    public ErrorResponse(){}

    public ErrorResponse(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

}
