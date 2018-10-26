package com.zws.core.token;



import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.List;
import java.util.Map;

public interface IndexNameOauth2Store extends TokenStore {

    String PRINCIPAL_NAME_INDEX_NAME = IndexNameOauth2Store.class.getName()
            .concat(".PRINCIPAL_NAME_INDEX_NAME");

    Map<String, String> findByIndexNameAndIndexValue(String indexName, String indexValue);

    void saveIndexName(String indexName,String indexValue);




}
