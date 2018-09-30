package com.zws.core.support;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/30
 */
public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static String writeValueAsString(Object object){
        if(object==null){
            return "";
        }
        try{
            return  objectMapper.writeValueAsString(object);
        }catch (Exception e){
            return "";
        }
    }
}
