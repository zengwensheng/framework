/**
 * 
 */
package com.zws.browser.session;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zws.core.support.ErrorEnum;
import com.zws.core.support.SimpleResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/29
 */
public abstract class AbstractSessionStrategy {

	private final Logger logger = LoggerFactory.getLogger(getClass());


	private String destinationUrl;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	private boolean createNewSession = true;
	
	private ObjectMapper objectMapper = new ObjectMapper();


	public AbstractSessionStrategy(String invalidSessionUrl) {
		Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
		this.destinationUrl = invalidSessionUrl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.session.InvalidSessionStrategy#
	 * onInvalidSessionDetected(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	protected void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {

		if (createNewSession) {
			request.getSession();
		}

		String sourceUrl = request.getRequestURI();
		String targetUrl;

		if (StringUtils.endsWithIgnoreCase(sourceUrl, ".html")) {
			targetUrl = destinationUrl+".html";
			redirectStrategy.sendRedirect(request, response, targetUrl);
		}else{
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(getErrorEnum())));
		}
		
	}


    abstract ErrorEnum getErrorEnum();

	public void setCreateNewSession(boolean createNewSession) {
		this.createNewSession = createNewSession;
	}
	
}
