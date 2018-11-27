package com.zws.features.mark;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/27
 */
@Configuration
public class MarkConfig {


    @Bean
    @Mark
    public MarkBean markBean1(){
        return new MarkBean(true);
    }

    @Bean
    public MarkBean markBena2(){
        return new MarkBean(false);
    }
}
