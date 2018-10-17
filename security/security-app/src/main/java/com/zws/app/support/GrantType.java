package com.zws.app.support;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/10/17
 */
public enum GrantType {

    AUTHORIZATION_CODE("1","authorization_code"),

    PASSWORD("2","password"),

    CLIENT_CREDENTIALS("3","client_credentials"),

    IMPLICIT("4","implicit"),

    REFRESH_TOKEN("5","refresh_token"),

    CUSTOM("6","custom")
    ;


    private String key;
    private String value;
    GrantType(String key,String value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
