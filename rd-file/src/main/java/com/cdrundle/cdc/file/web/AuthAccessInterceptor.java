/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.file.web;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import com.cdrundle.cdc.file.service.exception.NoAppKeyException;
import com.cdrundle.cdc.file.service.exception.NoTokenException;
import com.cdrundle.cdc.security.auth.service.ITokenAuthApi;
import com.cdrundle.cdc.security.auth.service.bo.AuthInfoBO;
import com.cdrundle.cdc.security.auth.service.bo.AuthUserInfoBO;
import com.cdrundle.common.exception.BaseException;

/**
 * <P>
 * 认证拦截器
 * <P>
 * 
 * @author tjee
 * @CreateDate 2017年5月22日 下午4:45:09
 */
public class AuthAccessInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(AuthAccessInterceptor.class);

	public static final String TOKEN_PARAM = "token";

	private static final String APPKEY_PARAM = "appKey";

	private static final String REQUEST_OPTIONS = "OPTIONS";

	@Resource(name = "tokenAuthApi")
	private ITokenAuthApi tokenAuthApi;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#
	 * preHandle(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestUrl = request.getRequestURL().toString();
		logger.debug("request method={}", request.getMethod());
		logger.debug("request url={}", requestUrl);
		if (REQUEST_OPTIONS.equals(request.getMethod())) {
			return true;
		}
		/** 静态资源不拦截 **/
		if (handler instanceof DefaultServletHttpRequestHandler) {
			return true;
		}
		// 验证token是否合法,并返回token对应的用户信息
		AuthInfoBO tokenInfo = resolverRequest(request);
		AuthUserInfoBO user = tokenAuthApi.tokenAuth(tokenInfo);
		Cookie cookie1 = new Cookie("appKey", "CDC_PCMS");
		Cookie cookie2 = new Cookie("token", tokenInfo.getToken());
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		// 保存信息到请求中
		request.setAttribute(AuthUser.class.getName(), user);
		return true;
	}

	private AuthInfoBO resolverRequest(HttpServletRequest request) throws BaseException {
		AuthInfoBO authInfoBO = new AuthInfoBO();
		resolverAppKey(authInfoBO, request);
		resolverToken(authInfoBO, request);
		resolverIP(authInfoBO, request);
		return authInfoBO;
	}

	/**
	 * 解析获取Token
	 * 
	 * @param authInfoBO
	 * @param request
	 * @throws NoTokenException
	 */
	private void resolverToken(AuthInfoBO authInfoBO, HttpServletRequest request) throws BaseException {
		// 从request中获取
		authInfoBO.setToken(request.getParameter(TOKEN_PARAM));
		// 从header中获取
		if (StringUtils.isBlank(authInfoBO.getToken())) {
			authInfoBO.setToken(request.getHeader(TOKEN_PARAM));
		}
		// 从cookies中获取
		if (StringUtils.isBlank(authInfoBO.getToken())) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (TOKEN_PARAM.equals(cookie.getName())) {
						authInfoBO.setToken(cookie.getValue());
						break;
					}
				}
			}
		}
		logger.debug("token={}", authInfoBO.getToken());
		if (StringUtils.isBlank(authInfoBO.getToken())) {
			throw new NoTokenException();
		}
	}

	/**
	 * 解析调用者appkey
	 * 
	 * @param authInfoBO
	 * @param request
	 * @throws NoAppKeyException
	 */
	private void resolverAppKey(AuthInfoBO authInfoBO, HttpServletRequest request) throws BaseException {
		// 从request中获取
		authInfoBO.setAppKey(request.getParameter(APPKEY_PARAM));
		// 从header中获取
		if (StringUtils.isBlank(authInfoBO.getAppKey())) {
			authInfoBO.setAppKey(request.getHeader(APPKEY_PARAM));
		}
		// 从cookies中获取
		if (StringUtils.isBlank(authInfoBO.getAppKey())) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (APPKEY_PARAM.equals(cookie.getName())) {
						authInfoBO.setAppKey(cookie.getValue());
						break;
					}
				}
			}
		}
		logger.debug("appkey={}", authInfoBO.getAppKey());
		if (StringUtils.isBlank(authInfoBO.getAppKey())) {
			throw new NoAppKeyException();
		}
	}

	/**
	 * 解析调用者IP
	 * 
	 * @param authInfoBO
	 * @param request
	 */
	private void resolverIP(AuthInfoBO authInfoBO, HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		authInfoBO.setIp((ip.indexOf(",") > 0) ? StringUtils.split(ip, "\\,")[0] : ip);
	}

}
