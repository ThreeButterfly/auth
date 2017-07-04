package com.cdrundle.cdc.security.auth.orm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cdrundle.cdc.security.auth.common.orm.CodeNameEntity;

/**
 * 用户角色
 * 
 * @author ccl
 * 
 */
@Entity
@Table(name = "t_cdc_mdm_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RoleDO extends CodeNameEntity {

	private static final long serialVersionUID = -3157645468291805265L;

	/**
	 * 描述
	 */
	@Column(length = 500)
	private String description;

	/**
	 * 是否启用
	 */
	@Column(name = "is_enable")
	private Boolean isEnable = false;

	/**
	 * 用户
	 */
	@ManyToMany(mappedBy="roles")
	private List<UserDO> users;

	/**
	 * 角色可使用的菜单
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_cdc_mdm_role_menu", 
	joinColumns = { @JoinColumn(name = "role_id") }, 
	inverseJoinColumns = {@JoinColumn(name = "menu_id") })
	private List<MenuDO> menus;
	
//	/**
//	 * 角色可使用的报表模板
//	 */
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "t_cdc_mdm_role_report", 
//	joinColumns = { @JoinColumn(name = "role_id") }, 
//	inverseJoinColumns = {@JoinColumn(name = "report_id") })
//	private List<ReportDO> reports;

	/**
	 * 是否是临时角色
	 */
	@Column(name = "is_temporary")
	private Boolean isTemporary;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	/**
	 * 过期时间
	 */
	@Column(name = "expiry_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryTime;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the isEnable
	 */
	public Boolean getIsEnable() {
		return isEnable;
	}

	/**
	 * @param isEnable
	 *            the isEnable to set
	 */
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public List<UserDO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDO> users) {
		this.users = users;
	}


	/**
	 * @return the isTemporary
	 */
	public Boolean getIsTemporary() {
		return isTemporary;
	}

	/**
	 * @param isTemporary
	 *            the isTemporary to set
	 */
	public void setIsTemporary(Boolean isTemporary) {
		this.isTemporary = isTemporary;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the expiryTime
	 */
	public Date getExpiryTime() {
		return expiryTime;
	}

	/**
	 * @param expiryTime
	 *            the expiryTime to set
	 */
	public void setExpiryTime(Date expiryTime) {
		this.expiryTime = expiryTime;
	}

	/**
	 * @return the menus
	 */
	public List<MenuDO> getMenus() {
		return menus;
	}

	/**
	 * @param menus the menus to set
	 */
	public void setMenus(List<MenuDO> menus) {
		this.menus = menus;
	}


	
}
