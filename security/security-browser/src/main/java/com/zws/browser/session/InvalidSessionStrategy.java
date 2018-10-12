/**
 * 
 */
package com.zws.browser.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zws.core.support.ErrorEnum;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/29
 */
public class InvalidSessionStrategy extends AbstractSessionStrategy implements org.springframework.security.web.session.InvalidSessionStrategy {

	public InvalidSessionStrategy(String invalidSessionUrl) {
		super(invalidSessionUrl);
	}

	@Override
	public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		onSessionInvalid(request, response);
	}

	@Override
	ErrorEnum getErrorEnum() {
		return ErrorEnum.SESSION_CONCURRENT;
	}
}
