package com.zws.core.config;

import com.zws.core.validate.ValidateController;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/29
 */
@Configuration
@Import(ValidateController.class)
public class ValidateCodeBeanConfig {

}
