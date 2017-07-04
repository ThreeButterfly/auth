/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.service.bo;

import java.io.Serializable;

import com.cdrundle.common.annotation.FieldCheck;
import com.cdrundle.common.annotation.FieldCheck.CheckType;


/**
 *<P>
 *  登陆认证信息
 *<P>
 * @author limaojun
 * @CreateDate 2017年1月4日 上午9:05:02
 */
public class LoginAuthBO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 709693373124829376L;
	
	public LoginAuthBO(String userName,String password,String appKey,String ip) {
		this.userName=userName;
		this.appKey=appKey;
		this.ip=ip;
		this.password=password;
	}
	
	/**
	 * 登陆用户
	 */
	@FieldCheck(type=CheckType.NOTBLANK)
	private String userName;
	/**
	 * 密码
	 */
	@FieldCheck(type=CheckType.NOTBLANK)
	private String password;
	/**
	 * 登陆应用
	 */
	@FieldCheck(type=CheckType.NOTBLANK)
	private String appKey;
	/**
	 * 登陆IP
	 */
	@FieldCheck(type=CheckType.NOTBLANK)
	private String ip;
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the appKey
	 */
	public String getAppKey() {
		return appKey;
	}

	/**
	 * @param appKey the appKey to set
	 */
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoginAuthBO [userName=").append(userName).append(", password=").append(password)
				.append(", appKey=").append(appKey).append(", ip=").append(ip).append("]");
		return builder.toString();
	}
	
	
}
