package com.zws.browser.support;

import lombok.Data;

/**
 * @author zsws
 * @email 2848392861@qq.com
 * date 2018/9/28
 */
@Data
public class SimpleResponse {

    private String content;

    public SimpleResponse(String content) {
        this.content = content;
    }
}
