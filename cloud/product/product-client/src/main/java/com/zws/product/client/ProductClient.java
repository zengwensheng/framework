package com.zws.product.client;

import com.zws.product.common.vo.ProductInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/4
 *
 * @Todo feign源码未解析
 */
@FeignClient(name = "product",path = "/product")
public interface ProductClient {

    /**
     * 注：@RequestBody 如果用了这个注解，方法是用post请求提交，就算指定为get请求，也没用
     * @param productIds
     * @return
     */
    @PostMapping("/findProductInfoByProductIdIn")
    List<ProductInfoVO> findProductInfoByProductIdIn(@RequestBody List<String> productIds);

}
