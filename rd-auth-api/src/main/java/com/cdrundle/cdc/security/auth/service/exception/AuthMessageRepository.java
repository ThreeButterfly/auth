/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.service.exception;

import com.cdrundle.common.exception.AbstractMessageRepository;

/**
 * <P>
 * 认证平台异常提示信息库
 * <P>
 * 
 * @author limaojun
 * @CreateDate 2016年12月29日 下午2:41:37
 */
public class AuthMessageRepository extends AbstractMessageRepository {
	
	private AuthMessageRepository() {
	};

	public static  AuthMessageRepository getInstance(){
		return RepositoryHolder.REPOSITORY;
	}
	
	private static class RepositoryHolder{
		private static final AuthMessageRepository REPOSITORY = new AuthMessageRepository();
	}
	
	/**
	 * 会话不存在
	 */
	public static final String TOKEN_NOT_EXIST = "TOKEN_NOT_EXIST";
	/**
	 * 会话过期
	 */
	public static final String TOKEN_EXPIRED = "TOKEN_EXPIRED";
	/**
	 * 会话超时
	 */
	public static final String TOKEN_TIMEOUT = "TOKEN_TIMEOUT";
	/**
	 * 会话失效
	 */
	public static final String TOKEN_INVALID = "TOKEN_INVALID";
	/**
	 * IP不合法
	 */
	public static final String IP_ILLEGAL = "IP_ILLEGAL";
	/**
	 * 用户名或密码错误
	 */
	public static final String USER_OR_PASSWORD_WRONG = "USER_OR_PASSWORD_WRONG";
	/**
	 * 用户状态错误
	 */
	public static final String USER_STATUS_WRONG = "USER_STATUS_WRONG";
	/**
	 * 生成Token异常
	 */
	public static final String GENERATE_TOKEN_EXCEPTION = "GENERATE_TOKEN_EXCEPTION";
	/**
	 * 移除TOKEN异常
	 */
	public static final String REMOVE_TOKEN_EXCEPTION = "REMOVE_TOKEN_EXCEPTION";
	/**
	 * 验证TOKEN异常
	 */
	public static final String TOKEN_CHECKED_EXCEPTION = "TOKEN_CHECKED_EXCEPTION";
	
	/**
	 * 不允许重复登陆
	 */
	public static final String ONLY_ONE_LOGIN = "ONLY_ONE_LOGIN";
	
	/**
	 * 无权限
	 */
	public static final String  NO_PRIVILEGE = "NO_PRIVILEGE";
	
	/**
	 * 错误的appKey
	 */
	public static final String WRONG_APP_KEY = "WRONG_APP_KEY";
	

	private String file =  "auth_message.properties";
	
	/* (non-Javadoc)
	 * @see com.cdrundle.common.exception.AbstractMessageRepository#getMessageFilePath()
	 */
	@Override
	public String getMessageFilePath() {
		return file;
	}
	
	
}
