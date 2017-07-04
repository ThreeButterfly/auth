/******************************************************************
 *
 *    Company:   成都市润东实业有限公司 软件开发部
 *
 *    Filename:    FileDO.java
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    @author:     Tjee
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年5月16日 上午10:13:14
 *
 *    Revision:
 *
 *    2017年5月16日 上午10:13:14
 *        - first revision
 *
 *****************************************************************/
package com.cdrundle.cdc.file.orm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * <P>
 * TODO：文件记录
 * <P>
 * 
 * @author Tjee
 * @CreateDate 2017年5月16日 上午10:13:14
 */
@Entity
@Table(name = "t_cdc_file_record")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FileRecordDO  extends FileBaseDO {
	private static final long serialVersionUID = -4932708405005614429L;
	 
	@Column(name = "user_id", length = 50, nullable = false)
	private Long userId;

	@Column(name = "user_name", length = 50)
	private String userName;

	@Column(name = "app_key")
	private String appKey;

	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@Column(name = "update_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
