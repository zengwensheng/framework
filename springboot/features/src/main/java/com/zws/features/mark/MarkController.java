package com.zws.features.mark;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/27
 */
@RestController
@Slf4j
public class MarkController {


    /**
     * 获取mark注解标注的bean
     * bean的定义见 {@link MarkConfig}
     * 借鉴于 spring-cloud-commons的 @LoadBalanced与LoadBalancerAutoConfiguration的配置类
     */
    @Autowired(required = false)
    @Mark
    private List<MarkBean> markBeanList;

    @GetMapping("/mark")
    public List<MarkBean> mark(){
        log.info("获取标记的bean的大小： {}",markBeanList!=null?markBeanList.size():0);
        return markBeanList;
    }
}
