package com.zws.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/29
 */
@ConfigurationProperties("security.core")
@Data
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    private ImageCodeProperties imageCode = new ImageCodeProperties();


}
