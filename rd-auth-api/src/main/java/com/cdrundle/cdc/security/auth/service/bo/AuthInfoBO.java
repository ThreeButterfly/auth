/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.service.bo;

import java.io.Serializable;

import com.cdrundle.common.annotation.FieldCheck;
import com.cdrundle.common.annotation.FieldCheck.CheckType;


/**
 *<P>
 *  认证信息
 *<P>
 * @author limaojun
 * @CreateDate 2017年1月3日 下午5:14:02
 */
public class AuthInfoBO implements Serializable {

	private static final long serialVersionUID = -384705850186220123L;
	@FieldCheck(type=CheckType.NOTBLANK)
	private String token;
	@FieldCheck(type=CheckType.NOTBLANK)
	private String ip;
	@FieldCheck(type=CheckType.NOTBLANK)
	private String appKey;
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthInfoBO [token=").append(token).append(", ip=").append(ip).append(", appKey=").append(appKey)
				.append("]");
		return builder.toString();
	}
	
}
