package com.zws.common.validate;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/4
 */
@Configuration
@Import({ValidateAspect.class,ControllerException.class})
public class ValidateAutoConfiguration {
}
