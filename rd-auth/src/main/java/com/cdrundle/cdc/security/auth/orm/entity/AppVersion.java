package com.cdrundle.cdc.security.auth.orm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cdrundle.cdc.security.auth.common.orm.LongIdEntity;

/**
 * 应用版本
 * @author lihao
 *
 */
@Entity
@Table(name = "t_cdc_mdm_app_version")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppVersion extends LongIdEntity{

	private static final long serialVersionUID = 2894753908211017651L;
	
	/**
	 * 版本号
	 */
	@Column(name = "code")
	private String code;
	
	/**
	 * 版本名称
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * MD5码
	 */
	@Column(name = "md5_code")
	private String md5Code;

	/**
	 * 版本说明
	 */
	@Column(name = "description")
	private String description;
	
	/**
	 * 发布时间
	 */
	@Column(name = "publish_time")
	@Temporal(TemporalType.DATE)
	private Date publishTime;
	
	/**
	 * 应用系统
	 */
	@OneToMany(mappedBy = "appVersion")
	private List<AppDO> apps;
	
	/**
	 * 是否为当前主版本
	 */
	@Column(name = "is_main_version")
	private Boolean isMainVersion;
	
	/**
	 * 版本文件存放路径
	 */
	@Column(name = "save_path")
	private String savePath;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getMd5Code() {
		return md5Code;
	}

	public void setMd5Code(String md5Code) {
		this.md5Code = md5Code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Boolean getIsMainVersion() {
		return isMainVersion;
	}

	public void setIsMainVersion(Boolean isMainVersion) {
		this.isMainVersion = isMainVersion;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public List<AppDO> getApps() {
		return apps;
	}

	public void setApps(List<AppDO> apps) {
		this.apps = apps;
	}

	

	
}
