package com.zws.client.authentication;

import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/5
 */
public class HttpHeaderResourceOwnerPasswordAccessTokenProvider extends ResourceOwnerPasswordAccessTokenProvider {

    public OAuth2AccessToken refreshAccessToken(OAuth2ProtectedResourceDetails resource,
                                                OAuth2RefreshToken refreshToken, AccessTokenRequest request) throws UserRedirectRequiredException,
            OAuth2AccessDeniedException {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
        form.add("grant_type", "refresh_token");
        form.add("refresh_token", refreshToken.getValue());
        return retrieveToken(request, resource, form,getHttpHeaders(request));
    }

    public OAuth2AccessToken obtainAccessToken(OAuth2ProtectedResourceDetails details, AccessTokenRequest request)
            throws UserRedirectRequiredException, AccessDeniedException, OAuth2AccessDeniedException {

        ResourceOwnerPasswordResourceDetails resource = (ResourceOwnerPasswordResourceDetails) details;

        return retrieveToken(request, resource, getParametersForTokenRequest(resource, request),getHttpHeaders(request));

    }

    private HttpHeaders getHttpHeaders(AccessTokenRequest request){
        Map<? extends String, ? extends List<String>> headers = request.getHeaders();
        HttpHeaders httpHeaders = new HttpHeaders();
        for(String key:headers.keySet()){
            List<String> headersValueList = headers.get(key);
            String headersValue ="";
            if(headersValueList!=null&&headersValueList.size()>=0){
                headersValue = headersValueList.get(0);
            }
            if(StringUtils.isEmpty(headersValue)){
                continue;
            }
            httpHeaders.add(key,headersValue);
        }
        return httpHeaders;
    }

    private MultiValueMap<String, String> getParametersForTokenRequest(ResourceOwnerPasswordResourceDetails resource, AccessTokenRequest request) {

        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
        form.set("grant_type", "password");

        form.set("username", resource.getUsername());
        form.set("password", resource.getPassword());
        form.putAll(request);

        if (resource.isScoped()) {

            StringBuilder builder = new StringBuilder();
            List<String> scope = resource.getScope();

            if (scope != null) {
                Iterator<String> scopeIt = scope.iterator();
                while (scopeIt.hasNext()) {
                    builder.append(scopeIt.next());
                    if (scopeIt.hasNext()) {
                        builder.append(' ');
                    }
                }
            }

            form.set("scope", builder.toString());
        }

        return form;

    }
}
