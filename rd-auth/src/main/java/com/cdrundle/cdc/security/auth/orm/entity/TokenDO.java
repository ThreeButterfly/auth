/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.orm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 *<P>
 *  会话表
 *<P>
 * @author limaojun
 * @CreateDate 2016年12月29日 下午3:50:11
 */
@Entity
@Table(name="t_cdc_auth_token")
public class TokenDO {

	@Id
	@GeneratedValue(generator="tokengenerator")
	@GenericGenerator(name="tokengenerator",strategy="uuid")
	@Column(name="token_id")
	private String id;
	
	/**
	 * token
	 */
	@Column(name="token_code")
	private String tokenCode;
	
	/**
	 * 所属用户ID
	 */
	@Column(name="user_id")
	private String userId;
	
	/**
	 * 用户名称
	 */
	@Column(name="user_name")
	private String userName;
	
	/**
	 * 登陆IP
	 */
	@Column(name="login_ip")
	private String loginIp;
	
	/**
	 * 登陆时间
	 */
	@Column(name="login_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginTime;
	
	/**
	 * 失效时间
	 */
	@Column(name="expired_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiredTime;
	
	/**
	 * 是否激活
	 */
	@Column(name="is_active")
	private boolean isActive=false;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the tokenCode
	 */
	public String getTokenCode() {
		return tokenCode;
	}

	/**
	 * @param tokenCode the tokenCode to set
	 */
	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

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
	 * @return the loginIp
	 */
	public String getLoginIp() {
		return loginIp;
	}

	/**
	 * @param loginIp the loginIp to set
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	/**
	 * @return the loginTime
	 */
	public Date getLoginTime() {
		return loginTime;
	}

	/**
	 * @param loginTime the loginTime to set
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * @return the expiredTime
	 */
	public Date getExpiredTime() {
		return expiredTime;
	}

	/**
	 * @param expiredTime the expiredTime to set
	 */
	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TokenDO [id=");
		builder.append(id);
		builder.append(", tokenCode=");
		builder.append(tokenCode);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", loginIp=");
		builder.append(loginIp);
		builder.append(", loginTime=");
		builder.append(loginTime);
		builder.append(", expiredTime=");
		builder.append(expiredTime);
		builder.append(", isActive=");
		builder.append(isActive);
		builder.append("]");
		return builder.toString();
	}
	
}
