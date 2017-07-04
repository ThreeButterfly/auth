/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.cdc.security.auth.orm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.cdrundle.common.enums.CurrentEnabledStatusEnum;

/**
 *<P>
 *  系统应用表
 *<P>
 * @author limaojun
 * @CreateDate 2017年1月11日 上午9:49:43
 */
@Entity
@Table(name="t_cdc_mdm_app")
public class AppDO implements Serializable{
	
	private static final long serialVersionUID = 4691899511793523832L;
	/**
	 * 应用ID
	 */
	@Id
	@GeneratedValue(generator="appgenerator")
	@GenericGenerator(name="appgenerator",strategy="uuid")
	@Column(name="app_id")
	private String id;
	/**
	 * 应用名称
	 */
	@Column(name="app_name",length=50,nullable=false,unique=true)
	private String name;
	/**
	 * 应用Key
	 */
	@Column(name="app_key",length=50,nullable=false,unique=true)
	private String appKey;
	/**
	 * 安全认证key
	 */
	@Column(name="security_key",length=20)
	private String securityKey;
	/**
	 * 应用描述
	 */
	@Column(name="app_description",length=500)
	private String description;
	/**
	 * 应用状态
	 */
	@Column(name="status",nullable=false)
	@Enumerated(EnumType.ORDINAL)
	private CurrentEnabledStatusEnum status;
	
	/**
	 * 应用版本
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "app_version_id")
	private AppVersion appVersion;
	/**
	 * 备注
	 */
	@Column(name="remark",length=500)
	private String remark;
	
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the securityKey
	 */
	public String getSecurityKey() {
		return securityKey;
	}
	/**
	 * @param securityKey the securityKey to set
	 */
	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the status
	 */
	public CurrentEnabledStatusEnum getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(CurrentEnabledStatusEnum status) {
		this.status = status;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public AppVersion getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(AppVersion appVersion) {
		this.appVersion = appVersion;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "App [id=" + id + ", name=" + name + ", appKey=" + appKey + ", securityKey=" + securityKey
				+ ", description=" + description + ", status=" + status + ", remark=" + remark + "]";
	}
	
	
}
