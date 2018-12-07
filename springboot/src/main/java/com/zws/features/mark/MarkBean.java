package com.zws.features.mark;

import lombok.Data;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/27
 */
@Data
public class MarkBean {

    /**
     * 是否标记
     */
    private boolean mark;


    public MarkBean(boolean mark){
        this.mark = mark;
    }




}
